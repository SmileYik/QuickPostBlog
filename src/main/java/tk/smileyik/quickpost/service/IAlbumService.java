package tk.smileyik.quickpost.service;

import tk.smileyik.quickpost.entity.Item;

import java.util.List;

public interface IAlbumService {
  /**
   * 发表一篇新的文章。
   * @param blogId
   * @param albumId
   * @param item
   * @param includeByItem
   * @param idx
   * @return
   */
  boolean post(String blogId, String albumId, Item item, String includeByItem, int idx);

  /**
   * 修改某一篇文章但是不修改文章所在的位置。
   * @param blogId
   * @param albumId
   * @param item
   * @return
   */
  boolean modifyPost(String blogId, String albumId, Item item);

  /**
   * 修改某一篇文章， 并同时修改它所在的位置。
   * @param blogId
   * @param albumId
   * @param item
   * @param includeByItem
   * @param idx
   * @return
   */
  boolean modifyPost(String blogId, String albumId, Item item, String includeByItem, int idx);

  /**
   * 获取某一篇指定文章的详细内容，prev被替换为文章的所有内容。
   * @param blogId
   * @param albumId
   * @param itemId
   * @return
   */
  Item getPost(String blogId, String albumId, String itemId);

  /**
   * 获取博客的指定文章集下所有的文章。
   * @param blogId
   * @param albumId
   * @return
   */
  List<Item> getAllItems(String blogId, String albumId);

  /**
   * 删除一篇文章。
   * @param blogId
   * @param albumId
   * @param itemId
   * @return
   */
  Item deletePost(String blogId, String albumId, String itemId);
}
