package tk.smileyik.quickpost.entity;

import java.io.Serializable;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 16:30
 */
public class SimpleAlbum implements Serializable {
  private String id;
  private String author;
  private String title;
  private Long modifyTime;

  public SimpleAlbum() {

  }

  public SimpleAlbum(String id, String author, String title, Long modifyTime) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.modifyTime = modifyTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(Long modifyTime) {
    this.modifyTime = modifyTime;
  }

  @Override
  public String toString() {
    return "SimpleAlbum{" +
        "id='" + id + '\'' +
        ", author='" + author + '\'' +
        ", title='" + title + '\'' +
        ", modifyTime=" + modifyTime +
        '}';
  }
}
