package tk.smileyik.quickpost.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.BlogConfiguration;
import tk.smileyik.quickpost.dao.IAlbumDao;
import tk.smileyik.quickpost.entity.Album;
import tk.smileyik.quickpost.entity.Item;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 8:55
 */
@Component("albumDaoImpl")
public class AlbumDaoImpl implements IAlbumDao {

  private BlogConfiguration blogConfiguration;

  @Autowired
  public void setBlogConfiguration(BlogConfiguration blogConfiguration) {
    this.blogConfiguration = blogConfiguration;
  }

  @Override
  public Album getAlbumById(String blogId, String albumId) {
    ObjectMapper objectMapper = new ObjectMapper();
    String path = blogConfiguration.getMarkdownAlbumsBase(blogId);
    File file = new File(path, albumId + ".json");
    if (file.exists() && file.isFile()) {
      try {
        return objectMapper.readValue(file, Album.class);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public boolean updateAlbum(String blogId, Album album) {
    String path = blogConfiguration.getMarkdownAlbumsBase(blogId);
    File file = new File(path, album.getId() + ".json");
    return file.exists() && file.isFile() && addAlbum(blogId, album);
  }

  @Override
  public boolean addAlbum(String blogId, Album album) {
    String path = blogConfiguration.getMarkdownAlbumsBase(blogId);
    File file = new File(path, album.getId() + ".json");
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(file, album);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean writeDownMarkdown(String blogId, Item post) {
    File markdownFile = new File(blogConfiguration.getMarkdownBase(blogId), post.getMarkdown());
    String markdownContent = post.getPrev();
    int tail = blogConfiguration.getPrevLength();
    if (tail > markdownContent.length()) {
      tail = markdownContent.length();
    }
    post.setPrev(markdownContent.substring(0, tail));
    try {
      Files.write(
          markdownFile.toPath(),
          markdownContent.getBytes(StandardCharsets.UTF_8),
          StandardOpenOption.CREATE,
          StandardOpenOption.WRITE,
          StandardOpenOption.TRUNCATE_EXISTING
      );
    } catch (IOException e) {
      e.printStackTrace();
      post.setPrev(markdownContent);
      return false;
    }
    return true;
  }

  @Override
  public String readMarkdown(String blogId, Item post) {
    File markdownFile = new File(blogConfiguration.getMarkdownBase(blogId), post.getMarkdown());
    if (markdownFile.exists() && markdownFile.isFile()) {
      try {
        String str = String.join(
            "\n",
            Files.readAllLines(markdownFile.toPath())
        );
        post.setPrev(str);
        return str;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public boolean deleteMarkdown(String blogId, Item post) {
    File markdownFile = new File(blogConfiguration.getMarkdownBase(blogId), post.getMarkdown());
    if (markdownFile.exists() && markdownFile.isFile()) {
      readMarkdown(blogId, post);
      return markdownFile.delete();
    }
    return false;
  }
}
