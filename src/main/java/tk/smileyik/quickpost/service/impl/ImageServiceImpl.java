package tk.smileyik.quickpost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.smileyik.quickpost.dao.IImageDao;
import tk.smileyik.quickpost.service.IImageService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月07日 11:34
 */
@Service
public class ImageServiceImpl implements IImageService {

  private IImageDao imageDao;

  @Autowired
  public void setImageDao(IImageDao imageDao) {
    this.imageDao = imageDao;
  }

  @Override
  public String uploadImage(byte[] bytes) {

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] digest = messageDigest.digest(bytes);
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b));
      }
      return imageDao.saveImage(bytes, sb.toString());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean deleteImage(String filename) {
    return imageDao.deleteImage(filename);
  }
}
