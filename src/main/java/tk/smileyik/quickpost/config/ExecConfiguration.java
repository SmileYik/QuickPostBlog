package tk.smileyik.quickpost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 17:01
 */
@Component("execConfiguration")
@ConfigurationProperties("quick-post.exec")
public class ExecConfiguration {
  private String startCommand = "cmd";

  private Map<String, String> commandMap = new HashMap<>();

  public String getStartCommand() {
    return startCommand;
  }

  public void setStartCommand(String startCommand) {
    this.startCommand = startCommand;
  }

  public void setCommandMap(Map<String, String> commandMap) {
    this.commandMap = commandMap;
  }

  public Map<String, String> getCommandMap() {
    return commandMap;
  }

  public String getCommand(String key) {
    return commandMap.getOrDefault(key, "");
  }
}
