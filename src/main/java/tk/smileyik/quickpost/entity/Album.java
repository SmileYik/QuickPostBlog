package tk.smileyik.quickpost.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 16:31
 */
public class Album extends SimpleAlbum implements Serializable {
  private List<Item> items;

  public Album() {

  }

  public Album(List<Item> items) {
    this.items = items;
  }

  public Album(String id, String author, String title, Long modifyTime, List<Item> items) {
    super(id, author, title, modifyTime);
    this.items = items;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "Album{" +
        super.toString() + "," +
        "items=" + items +
        '}';
  }
}
