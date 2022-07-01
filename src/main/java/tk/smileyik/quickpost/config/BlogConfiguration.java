package tk.smileyik.quickpost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 16:38
 */
@Component("blogConfiguration")
@ConfigurationProperties("quick-post.blog")
public class BlogConfiguration {
  /**
   * 博客根目录.
   *
   * <p>
   *   非博客项目目录， 是放置博客文章的目录.
   * </p>
   */
  private String root = "blogs";
  /**
   * 放置markdown文章目录.
   */
  private String markdownBase = "markdowns";
  /**
   * 放置文章集信息目录.
   */
  private String markdownAlbumsBase = "albums";
  /**
   * 最新发表文章文件名.
   */
  private String newestPost = "newest-post.json";
  /**
   * 博客中所有文章集概要.
   */
  private String albums = "all-albums.json";
  /**
   * 文章预览字数.
   */
  private int prevLength = 100;

  /**
   * 进行管理如发表文章的token.
   */
  private String adminToken = "123456";

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }

  public String getMarkdownBase() {
    return markdownBase;
  }

  public void setMarkdownBase(String markdownBase) {
    this.markdownBase = markdownBase;
  }

  public String getMarkdownAlbumsBase() {
    return markdownAlbumsBase;
  }

  public void setMarkdownAlbumsBase(String markdownAlbumsBase) {
    this.markdownAlbumsBase = markdownAlbumsBase;
  }

  public String getNewestPost() {
    return newestPost;
  }

  public void setNewestPost(String newestPost) {
    this.newestPost = newestPost;
  }

  public String getAlbums() {
    return albums;
  }

  public void setAlbums(String albums) {
    this.albums = albums;
  }

  public String getMarkdownBase(String blog) {
    return String.format("%s/%s/%s/", root, blog, markdownBase);
  }

  public String getMarkdownAlbumsBase(String blog) {
    return String.format("%s/%s/%s/", root, blog, markdownAlbumsBase);
  }

  public String getNewestPost(String blog) {
    return String.format("%s/%s/%s", root, blog, newestPost);
  }

  public String getAllAlbums(String blog) {
    return String.format("%s/%s/%s", root, blog, albums);
  }

  public List<String> getBlogs() {
    return Arrays.stream(Objects.requireNonNull(
        Paths.get(root).toFile().list()
    )).collect(Collectors.toList());
  }

  public void setPrevLength(int prevLength) {
    this.prevLength = prevLength;
  }

  public int getPrevLength() {
    return prevLength;
  }

  public void setAdminToken(String adminToken) {
    this.adminToken = adminToken;
  }

  public String getAdminToken() {
    return adminToken;
  }

  @Override
  public String toString() {
    return "BlogConfiguration{" +
        "root='" + root + '\'' +
        ", markdownBase='" + markdownBase + '\'' +
        ", markdownAlbumsBase='" + markdownAlbumsBase + '\'' +
        ", newestPost='" + newestPost + '\'' +
        ", albums='" + albums + '\'' +
        '}';
  }
}
