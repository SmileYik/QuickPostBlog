package tk.smileyik.quickpost.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.BlogConfiguration;
import tk.smileyik.quickpost.dao.ISimpleAlbumDao;
import tk.smileyik.quickpost.entity.SimpleAlbum;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 17:06
 */
@Component("simpleAlbumDao")
public class SimpleAlbumDaoImpl implements ISimpleAlbumDao {
  private BlogConfiguration blogConfiguration;

  @Autowired
  public void setBlogConfiguration(
      @Qualifier("blogConfiguration") BlogConfiguration blogConfiguration
  ) {
    this.blogConfiguration = blogConfiguration;
  }

  @Override
  public List<SimpleAlbum> getAllSimpleAlbums(String blog) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(
          new File(blogConfiguration.getAllAlbums(blog)),
          new TypeReference<List<SimpleAlbum>>(){}
      );
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
