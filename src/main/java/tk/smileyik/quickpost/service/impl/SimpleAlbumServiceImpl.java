package tk.smileyik.quickpost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.smileyik.quickpost.dao.ISimpleAlbumDao;
import tk.smileyik.quickpost.entity.SimpleAlbum;
import tk.smileyik.quickpost.service.ISimpleAlbumService;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 17:32
 */
@Service
public class SimpleAlbumServiceImpl implements ISimpleAlbumService {
  private ISimpleAlbumDao simpleAlbumDao;

  @Autowired
  public void setSimpleAlbumDao(ISimpleAlbumDao simpleAlbumDao) {
    this.simpleAlbumDao = simpleAlbumDao;
  }

  @Override
  public SimpleAlbum getSimpleAlbumById(String blog, String id) {
    List<SimpleAlbum> allSimpleAlbums = simpleAlbumDao.getAllSimpleAlbums(blog);
    for (SimpleAlbum simpleAlbum : allSimpleAlbums) {
      if (simpleAlbum.getId().equals(id)) {
        return simpleAlbum;
      }
    }
    return null;
  }

  @Override
  public List<SimpleAlbum> getAllSimpleAlbums(String blog) {
    return simpleAlbumDao.getAllSimpleAlbums(blog);
  }

  @Override
  public boolean addSimpleAlbum(String blog, SimpleAlbum album, String markdown) {
    return simpleAlbumDao.addSimpleAlbum(blog, album, markdown);
  }

  @Override
  public boolean updateSimpleAlbum(String blog, SimpleAlbum album, String markdown) {
    return simpleAlbumDao.updateSimpleAlbum(blog, album, markdown);
  }

  @Override
  public boolean deleteSimpleAlbum(String blog, String album) {
    return simpleAlbumDao.deleteSimpleAlbum(blog, album);
  }

  @Override
  public String getAlbumMarkdown(String blogId, String albumId) {
    return simpleAlbumDao.getAlbumMarkdown(blogId, albumId);
  }
}
