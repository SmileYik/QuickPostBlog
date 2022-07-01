package tk.smileyik.quickpost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 15:07
 */
@Component("gitConfiguration")
@ConfigurationProperties("quick-post.git")
public class GitConfiguration {
  private String remote;

}
