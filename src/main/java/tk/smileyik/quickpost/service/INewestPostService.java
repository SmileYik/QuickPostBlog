package tk.smileyik.quickpost.service;

import tk.smileyik.quickpost.entity.Item;
import tk.smileyik.quickpost.entity.NewestPost;

public interface INewestPostService {
  boolean updateNewestPost(String blogId, NewestPost newestPost);

  boolean updateNewestPost(String blogId, String albumId, Item newPostItem);
}
