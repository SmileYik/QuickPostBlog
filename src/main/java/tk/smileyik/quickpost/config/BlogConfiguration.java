package tk.smileyik.quickpost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 16:38
 */
@Component("blogConfiguration")
@ConfigurationProperties("quick-post.blog")
public class BlogConfiguration {
  private String root = "blogs";
  private String markdownBase = "markdowns";
  private String markdownAlbumsBase = "albums";
  private String newestPost = "newest-post.json";
  private String albums = "all-albums.json";

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
