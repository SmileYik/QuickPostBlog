package tk.smileyik.quickpost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.smileyik.quickpost.dao.IAlbumDao;
import tk.smileyik.quickpost.entity.Album;
import tk.smileyik.quickpost.entity.Item;
import tk.smileyik.quickpost.service.IAlbumService;
import tk.smileyik.quickpost.util.Pair;

import java.util.*;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 9:52
 */
@Service("albumServiceImpl")
public class AlbumServiceImpl implements IAlbumService {

  private IAlbumDao albumDao;

  @Autowired
  public void setAlbumDao(IAlbumDao albumDao) {
    this.albumDao = albumDao;
  }

  @Override
  public boolean post(String blogId, String albumId, Item item, String includeByItem, int idx) {
    Album album = albumDao.getAlbumById(blogId, albumId);
    List<Item> itemList = album.getItems();
    Item target = findTargetItem(new LinkedList<>(itemList), includeByItem);
    targetDown(target == null ? itemList : target.getItems(), item, idx);
    return albumDao.writeDownMarkdown(blogId, item) && albumDao.updateAlbum(blogId, album);
  }

  private Item findTargetItem(LinkedList<Item> queue, String includeByItem) {
    if (includeByItem == null || includeByItem.equals("null")) {
      return null;
    }
    Item target;
    while (!queue.isEmpty()) {
      target = queue.removeFirst();
      if (target.getId().equals(includeByItem)) {
        return target;
      }
      queue.addAll(target.getItems());
    }
    return null;
  }

  private void targetDown(List<Item> items, Item item, int idx) {
    if (idx < 0) {
      idx = 0;
    } else if (idx >= items.size()) {
      idx = items.size();
    }
    items.add(idx, item);
  }

  private Pair<Item, Integer> findTarget(List<Item> itemList, String itemId) {
    int idx = -1;
    for (Item item : itemList) {
      ++idx;
      if (itemId.equals(item.getId())) {
        return new Pair<>(null, idx);
      } else {
        Pair<Item, Integer> pair = findTarget(item.getItems(), itemId);
        if (pair != null && pair.getKey() == null) {
          return new Pair<>(item, pair.getValue());
        }
      }
    }
    return null;
  }

  @Override
  public boolean modifyPost(String blogId, String albumId, Item item) {
    Album album = albumDao.getAlbumById(blogId, albumId);
    List<Item> itemList = album.getItems();
    Pair<Item, Integer> target = findTarget(itemList, item.getId());
    if (target == null) {
      return false;
    } else if (target.getKey() == null) {
      album.getItems().set(target.getValue(), item);
    } else {
      target.getKey().getItems().set(target.getValue(), item);
    }
    return albumDao.writeDownMarkdown(blogId, item) && albumDao.updateAlbum(blogId, album);
  }

  @Override
  public boolean modifyPost(String blogId, String albumId, Item item, String includeByItem, int idx) {
    Album album = albumDao.getAlbumById(blogId, albumId);
    List<Item> itemList = album.getItems();
    Pair<Item, Integer> target = findTarget(itemList, item.getId());
    if (target == null) {
      return false;
    } else if (target.getKey() == null) {
      album.getItems().remove((int) target.getValue());
    } else {
      target.getKey().getItems().remove((int) target.getValue());
    }

    Item targetItem = findTargetItem(new LinkedList<>(itemList), includeByItem);
    targetDown(targetItem == null ? itemList : targetItem.getItems(), item, idx);
    return albumDao.writeDownMarkdown(blogId, item) && albumDao.updateAlbum(blogId, album);
  }

  @Override
  public Item getPost(String blogId, String albumId, String itemId) {
    Album album = albumDao.getAlbumById(blogId, albumId);
    List<Item> itemList = album.getItems();
    Pair<Item, Integer> target = findTarget(itemList, itemId);
    if (target == null) {
      return null;
    } else if (target.getKey() == null) {
      return album.getItems().get(target.getValue());
    } else {
      return target.getKey().getItems().get(target.getValue());
    }
  }

  @Override
  public List<Item> getAllItems(String blogId, String albumId) {
    Album album = albumDao.getAlbumById(blogId, albumId);
    LinkedList<Item> items = new LinkedList<>(album.getItems());
    List<Item> map = new ArrayList<>();
    while (!items.isEmpty()) {
      Item item = items.removeFirst();
      List<Item> sub = item.getItems();
      item.setItems(new ArrayList<>());
      map.add(item);
      items.addAll(0, sub);
    }
    return map;
  }
}

