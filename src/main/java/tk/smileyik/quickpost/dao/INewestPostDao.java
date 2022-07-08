package tk.smileyik.quickpost.dao;

import tk.smileyik.quickpost.entity.NewestPost;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月08日 13:51
 */
public interface INewestPostDao {
  boolean updateNewestPostList(String blogId, List<NewestPost> newestPosts);

  List<NewestPost> getNewestPostList(String blogId);
}
