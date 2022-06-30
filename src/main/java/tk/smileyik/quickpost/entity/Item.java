package tk.smileyik.quickpost.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 16:32
 */
public class Item implements Serializable {
  private String id;
  private String author;
  private List<Item> items;
  private String tag;
  private String title;
  private String prev;
  private String markdown;
  private Long modifyTime;
  private Long postTime;

  public Item() {

  }

  public Item(String id, String author, List<Item> items, String tag, String title, String prev, String markdown, Long modifyTime, Long postTime) {
    this.id = id;
    this.author = author;
    this.items = items;
    this.tag = tag;
    this.title = title;
    this.prev = prev;
    this.markdown = markdown;
    this.modifyTime = modifyTime;
    this.postTime = postTime;
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

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
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

  public String getPrev() {
    return prev;
  }

  public void setPrev(String prev) {
    this.prev = prev;
  }

  public String getMarkdown() {
    return markdown;
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
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

  @Override
  public String toString() {
    return "Item{" +
        "id='" + id + '\'' +
        ", author='" + author + '\'' +
        ", items=" + items +
        ", tag='" + tag + '\'' +
        ", title='" + title + '\'' +
        ", prev='" + prev + '\'' +
        ", markdown='" + markdown + '\'' +
        ", modifyTime=" + modifyTime +
        ", postTime=" + postTime +
        '}';
  }
}
