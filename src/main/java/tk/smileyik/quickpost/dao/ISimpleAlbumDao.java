package tk.smileyik.quickpost.dao;

import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.entity.SimpleAlbum;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 17:04
 */
public interface ISimpleAlbumDao {
  /**
   * 获取博客下所有文章集概要.
   *
   * @param blog 博客id
   * @return 该博客下的所有文章集概要
   */
  List<SimpleAlbum> getAllSimpleAlbums(String blog);
}
