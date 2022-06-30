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
  List<SimpleAlbum> getAllSimpleAlbums(String blog);
}
