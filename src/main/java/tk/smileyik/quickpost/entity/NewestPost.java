package tk.smileyik.quickpost.entity;

import java.io.Serializable;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月08日 13:53
 */
public class NewestPost implements Serializable {
  private String album;
  private String author;
  private String id;
  private String tag;
  private String title;
  private Long modifyTime;
  private Long postTime;

  public NewestPost() {

  }

  public NewestPost(String album, String author,
                    String id, String tag,
                    String title, Long modifyTime,
                    Long postTime) {
    this.album = album;
    this.author = author;
    this.id = id;
    this.tag = tag;
    this.title = title;
    this.modifyTime = modifyTime;
    this.postTime = postTime;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
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

  public Long getPostTime() {
    return postTime;
  }

  public void setPostTime(Long postTime) {
    this.postTime = postTime;
  }
}
