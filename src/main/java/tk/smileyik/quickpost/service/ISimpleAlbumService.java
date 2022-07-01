package tk.smileyik.quickpost.service;

import tk.smileyik.quickpost.entity.SimpleAlbum;

import java.util.List;

public interface ISimpleAlbumService {
  /**
   * 获取某一个博客的文章集概要.
   *
   * @param blog 博客id
   * @param id   文章集id
   * @return     指定文章集
   */
  SimpleAlbum getSimpleAlbumById(String blog, String id);

  /**
   * 获取指定博客的所有文章集概要.
   *
   * @param blog 博客id
   * @return     该博客下的所有文章集概要
   */
  List<SimpleAlbum> getAllSimpleAlbums(String blog);
}
