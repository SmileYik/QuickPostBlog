package tk.smileyik.quickpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.smileyik.quickpost.service.IBlogService;
import tk.smileyik.quickpost.util.Result;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 10:35
 */
@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

  private IBlogService blogService;

  @Autowired
  public void setBlogService(IBlogService blogService) {
    this.blogService = blogService;
  }

  @GetMapping
  public Result<List<String>> getBlogIds() {
    List<String> blogs = blogService.getBlogs();
    return new Result<>(blogs);
  }
}
