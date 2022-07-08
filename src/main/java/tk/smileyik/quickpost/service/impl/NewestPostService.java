package tk.smileyik.quickpost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.smileyik.quickpost.config.BlogConfiguration;
import tk.smileyik.quickpost.dao.INewestPostDao;
import tk.smileyik.quickpost.entity.Item;
import tk.smileyik.quickpost.entity.NewestPost;
import tk.smileyik.quickpost.service.INewestPostService;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月08日 14:08
 */
@Service
public class NewestPostService implements INewestPostService {

  private BlogConfiguration blogConfiguration;
  private INewestPostDao newestPostDao;

  @Autowired
  public void setBlogConfiguration(BlogConfiguration blogConfiguration) {
    this.blogConfiguration = blogConfiguration;
  }

  @Autowired
  public void setNewestPostDao(INewestPostDao newestPostDao) {
    this.newestPostDao = newestPostDao;
  }

  @Override
  public boolean updateNewestPost(String blogId, NewestPost newestPost) {
    List<NewestPost> newestPostList = newestPostDao.getNewestPostList(blogId);
    Iterator<NewestPost> iterator = newestPostList.iterator();
    // delete same id in this newest post list.
    while (iterator.hasNext()) {
      NewestPost next = iterator.next();
      if (next.getId().equals(newestPost.getId())) {
        iterator.remove();
        newestPostList.remove(next);
        break;
      }
    }
    // add to the first element at the list, and sort it.
    newestPostList.add(0, newestPost);
    newestPostList.sort(Comparator.comparingLong(NewestPost::getModifyTime).reversed());
    if (newestPostList.size() > blogConfiguration.getNewestPostSize()) {
      newestPostList = newestPostList.subList(0, blogConfiguration.getNewestPostSize());
    }
    return newestPostDao.updateNewestPostList(blogId, newestPostList);
  }

  @Override
  public boolean updateNewestPost(String blogId, String albumId, Item newPostItem) {
    return updateNewestPost(blogId, new NewestPost(
        albumId,
        newPostItem.getAuthor(),
        newPostItem.getId(),
        newPostItem.getTag(),
        newPostItem.getTitle(),
        newPostItem.getModifyTime(),
        newPostItem.getPostTime()
    ));
  }
}
