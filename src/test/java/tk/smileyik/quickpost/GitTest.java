package tk.smileyik.quickpost;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tk.smileyik.quickpost.config.GitConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 15:13
 */
@SpringBootTest
public class GitTest {
  @Test
  public void cloneRepositoryTest() throws GitAPIException, IOException {
    Runtime runtime = Runtime.getRuntime();
    Process exec = runtime.exec("echo aaa", null, new File("F:/"));
    OutputStream outputStream = exec.getOutputStream();
    outputStream.write("\nnpm install\n".getBytes(StandardCharsets.UTF_8));
    outputStream.write("exit\n".getBytes(StandardCharsets.UTF_8));
    outputStream.flush();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
    System.out.println(bufferedReader.lines().collect(Collectors.toList()));
    System.out.println(bufferedReader2.lines().collect(Collectors.toList()));
  }
}
