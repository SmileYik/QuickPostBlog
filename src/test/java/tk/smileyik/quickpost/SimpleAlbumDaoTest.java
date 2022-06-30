package tk.smileyik.quickpost;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import tk.smileyik.quickpost.dao.ISimpleAlbumDao;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 17:15
 */
@SpringBootTest
public class SimpleAlbumDaoTest {
  @Test
  public void getAllAlbumsTest(@Qualifier("simpleAlbumDao") ISimpleAlbumDao simpleAlbumDao) {
    System.out.println(simpleAlbumDao.getAllSimpleAlbums("readBook"));
  }
}
