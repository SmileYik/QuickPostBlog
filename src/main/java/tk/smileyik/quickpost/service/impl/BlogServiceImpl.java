package tk.smileyik.quickpost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.smileyik.quickpost.dao.IBlogDao;
import tk.smileyik.quickpost.service.IBlogService;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 8:39
 */
@Service("blogService")
public class BlogServiceImpl implements IBlogService {
  private IBlogDao blogDao;

  @Autowired
  public void setBlogDao(IBlogDao blogDao) {
    this.blogDao = blogDao;
  }

  @Override
  public List<String> getBlogs() {
    return blogDao.getBlogs();
  }
}
