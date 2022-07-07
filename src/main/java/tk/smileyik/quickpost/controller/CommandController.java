package tk.smileyik.quickpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.smileyik.quickpost.config.ExecConfiguration;
import tk.smileyik.quickpost.util.Result;

import java.util.Set;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月06日 17:06
 */
@RestController
@RequestMapping("/cmd")
public class CommandController {
  private ExecConfiguration execConfiguration;

  @Autowired
  public void setExecConfiguration(ExecConfiguration execConfiguration) {
    this.execConfiguration = execConfiguration;
  }

  @GetMapping
  public Result<Set<String>> getAllCommand() {
    return new Result<>(execConfiguration.getCommandMap().keySet());
  }
}
