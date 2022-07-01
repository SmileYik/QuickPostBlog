package tk.smileyik.quickpost;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 15:13
 */
@SpringBootTest
public class GitTest {
  @Test
  public void cloneRepositoryTest() throws GitAPIException {
    Git call = Git.cloneRepository()
        .setURI("https://github.com/SmileYik/QuickPostBlog.git")
        .setBranch("master")
        .setDirectory(new File("test"))
        .setRemote("origin")
        .call();
  }
}
