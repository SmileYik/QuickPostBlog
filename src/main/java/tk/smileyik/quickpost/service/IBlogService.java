package tk.smileyik.quickpost.service;

import java.util.List;

public interface IBlogService {
  /**
   * 获取所有博客id.
   *
   * @return 所有博客id
   */
  List<String> getBlogs();
}
