package tk.smileyik.quickpost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 17:01
 */
@Component("execConfiguration")
@ConfigurationProperties("quick-post.exec")
public class ExecConfiguration {
  private String startCommand = "cmd";

  private String npmDeployCommand = "npm deploy";

  public String getNpmDeployCommand() {
    return npmDeployCommand;
  }

  public void setNpmDeployCommand(String npmDeployCommand) {
    this.npmDeployCommand = npmDeployCommand;
  }

  public String getStartCommand() {
    return startCommand;
  }

  public void setStartCommand(String startCommand) {
    this.startCommand = startCommand;
  }
}
