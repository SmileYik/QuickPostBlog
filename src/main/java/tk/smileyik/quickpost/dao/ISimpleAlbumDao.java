package tk.smileyik.quickpost.dao;

import tk.smileyik.quickpost.entity.SimpleAlbum;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 17:04
 */
public interface ISimpleAlbumDao {
  /**
   * 获取博客下所有文章集概要.
   *
   * @param blog 博客id
   * @return 该博客下的所有文章集概要
   */
  List<SimpleAlbum> getAllSimpleAlbums(String blog);

  boolean updateSimpleAlbums(String blog, List<SimpleAlbum> albums);

  boolean addSimpleAlbum(String blog, SimpleAlbum simpleAlbum, String markdown);

  boolean updateSimpleAlbum(String blog, SimpleAlbum simpleAlbum, String markdown);

  boolean deleteSimpleAlbum(String blog, String simpleAlbumId);
}
