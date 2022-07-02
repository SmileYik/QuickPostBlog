package tk.smileyik.quickpost.service;

import tk.smileyik.quickpost.entity.Item;

import java.util.Map;

public interface IAlbumService {
  boolean post(String blogId, String albumId, Item item, String includeByItem, int idx);
  boolean modifyPost(String blogId, String albumId, Item item);
  boolean modifyPost(String blogId, String albumId, Item item, String includeByItem, int idx);
  Item getPost(String blogId, String albumId, String itemId);
  Map<String, Item> getAllItems(String blogId, String albumId);
}
