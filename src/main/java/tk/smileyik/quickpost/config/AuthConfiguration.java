package tk.smileyik.quickpost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 15:04
 */
@Component("authConfiguration")
@ConfigurationProperties("quick-post.auth")
public class AuthConfiguration {
  /**
   * 进行管理如发表文章的token.
   */
  private String adminToken = "123456";

  public void setAdminToken(String adminToken) {
    this.adminToken = adminToken;
  }

  public String getAdminToken() {
    return adminToken;
  }

}
