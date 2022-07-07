package tk.smileyik.quickpost.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.BlogConfiguration;
import tk.smileyik.quickpost.dao.ISimpleAlbumDao;
import tk.smileyik.quickpost.entity.Album;
import tk.smileyik.quickpost.entity.SimpleAlbum;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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

  @Override
  public boolean updateSimpleAlbums(String blog, List<SimpleAlbum> albums) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(
          new File(blogConfiguration.getAllAlbums(blog)),
          albums
      );
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean addSimpleAlbum(String blog, SimpleAlbum simpleAlbum, String markdown) {
    List<SimpleAlbum> simpleAlbums = getAllSimpleAlbums(blog);
    simpleAlbums.add(simpleAlbum);

    File markdownFile = new File(
        blogConfiguration.getMarkdownAlbumsBase(blog),
        simpleAlbum.getId() + ".md"
    );

    try {
      Files.write(
          markdownFile.toPath(),
          markdown.getBytes(StandardCharsets.UTF_8),
          StandardOpenOption.CREATE,
          StandardOpenOption.WRITE,
          StandardOpenOption.TRUNCATE_EXISTING
      );
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    File albumFile = new File(
        blogConfiguration.getMarkdownAlbumsBase(blog),
        simpleAlbum.getId() + ".json"
    );
    ObjectMapper objectMapper = new ObjectMapper();
    Album album = new Album();
    album.setAuthor(simpleAlbum.getAuthor());
    album.setId(simpleAlbum.getId());
    album.setTitle(simpleAlbum.getTitle());
    album.setModifyTime(simpleAlbum.getModifyTime());
    album.setItems(new ArrayList<>());

    try {
      objectMapper.writeValue(albumFile, album);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    return updateSimpleAlbums(blog, simpleAlbums);
  }

  @Override
  public boolean updateSimpleAlbum(String blog, SimpleAlbum simpleAlbum, String markdown) {
    List<SimpleAlbum> simpleAlbums = getAllSimpleAlbums(blog);
    for (SimpleAlbum temp : simpleAlbums) {
      if (temp.getId().equals(simpleAlbum.getId())) {
        temp.setId(simpleAlbum.getId());
        temp.setAuthor(simpleAlbum.getAuthor());
        temp.setTitle(simpleAlbum.getTitle());
        temp.setModifyTime(simpleAlbum.getModifyTime());

        // write markdown
        File markdownFile = new File(
            blogConfiguration.getMarkdownAlbumsBase(blog),
            simpleAlbum.getId() + ".md"
        );
        try {
          Files.write(
              markdownFile.toPath(),
              markdown.getBytes(StandardCharsets.UTF_8),
              StandardOpenOption.CREATE,
              StandardOpenOption.WRITE,
              StandardOpenOption.TRUNCATE_EXISTING
          );
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }

        return updateSimpleAlbums(blog, simpleAlbums);
      }
    }
    return false;
  }

  @Override
  public boolean deleteSimpleAlbum(String blog, String simpleAlbumId) {
    List<SimpleAlbum> simpleAlbums = getAllSimpleAlbums(blog);
    for (SimpleAlbum temp : simpleAlbums) {
      if (temp.getId().equals(simpleAlbumId)) {
        simpleAlbums.remove(temp);
        return new File(
            blogConfiguration.getMarkdownAlbumsBase(blog),
            simpleAlbumId + ".md"
        ).delete() && new File(
            blogConfiguration.getMarkdownAlbumsBase(blog),
            simpleAlbumId + ".json"
        ).delete() && updateSimpleAlbums(blog, simpleAlbums);
      }
    }
    return false;
  }
}
