package tk.smileyik.quickpost.util;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 10:17
 */
public class Pair <K, V> {
  private final K key;
  private final V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}
