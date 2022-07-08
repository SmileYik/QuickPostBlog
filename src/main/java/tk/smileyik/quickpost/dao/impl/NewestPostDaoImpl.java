package tk.smileyik.quickpost.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.BlogConfiguration;
import tk.smileyik.quickpost.dao.INewestPostDao;
import tk.smileyik.quickpost.entity.NewestPost;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月08日 13:56
 */
@Component
public class NewestPostDaoImpl implements INewestPostDao {

  private BlogConfiguration blogConfiguration;

  @Autowired
  public void setBlogConfiguration(BlogConfiguration blogConfiguration) {
    this.blogConfiguration = blogConfiguration;
  }

  @Override
  public boolean updateNewestPostList(String blogId, List<NewestPost> newestPosts) {
    File newestPostFile = new File(blogConfiguration.getNewestPost(blogId));
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(newestPostFile, newestPosts);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public List<NewestPost> getNewestPostList(String blogId) {
    ObjectMapper objectMapper = new ObjectMapper();
    File newestPostFile = new File(blogConfiguration.getNewestPost(blogId));
    try {
      return objectMapper.readValue(
          newestPostFile, new TypeReference<List<NewestPost>>() {}
      );
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }
}
