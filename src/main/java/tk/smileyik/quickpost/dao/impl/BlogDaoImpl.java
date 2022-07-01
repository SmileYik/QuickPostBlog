package tk.smileyik.quickpost.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.BlogConfiguration;
import tk.smileyik.quickpost.dao.IBlogDao;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 8:37
 */
@Component("blogDaoImpl")
public class BlogDaoImpl implements IBlogDao {
  private BlogConfiguration blogConfiguration;

  @Autowired
  public void setBlogConfiguration(BlogConfiguration blogConfiguration) {
    this.blogConfiguration = blogConfiguration;
  }

  @Override
  public List<String> getBlogs() {
    return blogConfiguration.getBlogs();
  }
}
