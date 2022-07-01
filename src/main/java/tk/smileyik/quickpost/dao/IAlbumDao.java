package tk.smileyik.quickpost.dao;

import tk.smileyik.quickpost.entity.Album;
import tk.smileyik.quickpost.entity.Item;

import java.io.File;

public interface IAlbumDao {
  /**
   * 通过文章集id和博客id来获取一个文章集。
   *
   * @param blogId  博客id
   * @param albumId 文章集id
   * @return        相对应的文章集
   */
  Album getAlbumById(String blogId, String albumId);

  /**
   * 更新一个文章集.
   *
   * @param blogId 博客id
   * @param album  文章集
   */
  boolean updateAlbum(String blogId, Album album);

  /**
   * 添加一个文章集.
   * @param blogId 博客id
   * @param album  文章集
   */
  boolean addAlbum(String blogId, Album album);

  boolean writeDownMarkdown(String blogId, Item post);
}
