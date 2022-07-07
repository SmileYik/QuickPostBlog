package tk.smileyik.quickpost.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.GitConfiguration;
import tk.smileyik.quickpost.dao.IImageDao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月07日 11:25
 */
@Component
public class ImageDaoImpl implements IImageDao {
  public GitConfiguration gitConfiguration;

  @Autowired
  public void setGitConfiguration(GitConfiguration gitConfiguration) {
    this.gitConfiguration = gitConfiguration;
  }

  @Override
  public String saveImage(byte[] bytes, String filename) {
    File imageFile = new File(gitConfiguration.getRepository(), "public");
    String filePath = getFilePath(filename);
    if (filePath.isEmpty()) {
      return null;
    }
    File saveTo = new File(imageFile, filePath);
    File parentFile = saveTo.getParentFile();
    if (!parentFile.exists() && !parentFile.mkdirs()) {
      return null;
    }
    try {
      Files.write(saveTo.toPath(), bytes);
      return filePath;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean deleteImage(String filename) {
    File imageFile = new File(gitConfiguration.getRepository(), "public");
    String filePath = getFilePath(filename);
    if (filePath.isEmpty()) {
      return false;
    }
    File saveTo = new File(imageFile, filePath);
    if (!saveTo.delete()) {
      return false;
    }

    // 迭代三次， 删除多余的文件夹。
    for (int i = 0; i < 3; ++i) {
      File parent = saveTo.getParentFile();
      if (Objects.requireNonNull(parent.listFiles()).length == 0) {
        parent.delete();
      }
      saveTo = parent;
    }

    return saveTo.delete() || !saveTo.exists();
  }

  /**
   * 根据生成的SHA-256十六进制字符串生成存放路径。
   * @param filename 对文件进行SHA-256计算后结果的十六进制显示。
   * @return
   */
  private String getFilePath(String filename) {
    if (filename.length() < 6) {
      return "";
    }
    String first = filename.substring(0, 2);
    String second = filename.substring(2, 4);
    String third = filename.substring(4, 6);
    return "blogImage/" + first + "/" + second + "/" + third + "/" + filename;
  }
}
