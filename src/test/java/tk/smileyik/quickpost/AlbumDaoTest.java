package tk.smileyik.quickpost;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.smileyik.quickpost.dao.IAlbumDao;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 8:59
 */
@SpringBootTest
public class AlbumDaoTest {
  @Autowired
  private IAlbumDao albumDao;

  @Test
  public void albumDaoTest() {
    System.out.println(albumDao.getAlbumById("other", "MinecraftPost"));
  }
}
