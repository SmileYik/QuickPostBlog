package tk.smileyik.quickpost.dao;

import java.util.List;

public interface IBlogDao {
  /**
   * 获取所有博客id.
   *
   * @return 所有博客id
   */
  List<String> getBlogs();
}

