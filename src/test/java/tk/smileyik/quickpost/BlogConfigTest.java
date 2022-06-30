package tk.smileyik.quickpost;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.smileyik.quickpost.config.BlogConfiguration;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年06月30日 17:21
 */
@SpringBootTest
public class BlogConfigTest {
  @Autowired
  private BlogConfiguration blogConfiguration;

  @Test
  public void rootTest() {
    System.out.println(blogConfiguration);
  }
}
