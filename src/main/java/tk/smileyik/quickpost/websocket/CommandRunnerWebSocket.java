package tk.smileyik.quickpost.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.AuthConfiguration;
import tk.smileyik.quickpost.config.ExecConfiguration;
import tk.smileyik.quickpost.config.GitConfiguration;
import tk.smileyik.quickpost.util.BeanUtil;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 16:39
 */
@Log4j2
@Component
@ServerEndpoint("/cmd/{token}/{command}")
public class CommandRunnerWebSocket {
  private Session session;
  private Process exec;
  private BufferedReader info;
  private BufferedReader error;
  private Thread infoThread;
  private Thread errorThread;
  private LogReporter infoReporter;
  private LogReporter errorReporter;

  @OnOpen
  public void onOpen(
      Session session,
      @PathParam("token") String token,
      @PathParam("command") String command
  ) throws IOException {

    AuthConfiguration authConfiguration = BeanUtil.getBean(AuthConfiguration.class);
    ExecConfiguration execConfiguration = BeanUtil.getBean(ExecConfiguration.class);
    GitConfiguration gitConfiguration = BeanUtil.getBean(GitConfiguration.class);

    if (!authConfiguration.getAdminToken().equals(token)) {
      log.info("Token不正确， 停止连接。");
      session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "i can not accept it!!!!"));
      return;
    }
    this.session = session;
    exec = Runtime.getRuntime().exec(
        execConfiguration.getStartCommand(),
        null,
        new File(gitConfiguration.getRepository())
    );
    OutputStream outputStream = exec.getOutputStream();
    log.info("运行如下指令：\n" + execConfiguration.getCommand(command));
    outputStream.write(execConfiguration.getCommand(command).getBytes(StandardCharsets.UTF_8));
    outputStream.flush();
    // 开始准备输出日志.
    info = new BufferedReader(new InputStreamReader(exec.getInputStream(), StandardCharsets.UTF_8));
    error = new BufferedReader(new InputStreamReader(exec.getErrorStream(), StandardCharsets.UTF_8));
    infoReporter = new LogReporter(info);
    errorReporter = new LogReporter(error);
    infoThread = new Thread(infoReporter);
    errorThread = new Thread(errorReporter);
    infoThread.start();
    errorThread.start();
    // 如果输出流都停止了工作那么就关闭连接
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        if (!infoThread.isAlive() && !errorThread.isAlive()) {
          try {
            try {
              session.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
            shutdownReport();
          } catch (IOException e) {
            throw new RuntimeException(e);
          } finally {
            cancel();
          }
        }
      }
    }, 1000L, 1000L);
  }

  @OnClose
  public void onClose() throws IOException {
    shutdownReport();
  }

  @OnError
  public void onError(Throwable t) {
    t.printStackTrace();
    try {
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      shutdownReport();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @OnMessage
  public void onMessage(String msg) {

  }

  private void shutdownReport() throws IOException {
    log.info("指令执行完毕， 关闭连接。");
    if (info != null) {
      infoReporter.setContinueReport(false);
      infoThread = null;
      info.close();
    }

    if (error != null) {
      errorReporter.setContinueReport(false);
      errorThread = null;
      error.close();
    }

    if (exec != null) {
      exec.destroy();
    }
  }

  private void sendMessage(String text) {
    try {
      session.getBasicRemote().sendText(text);
    } catch (IOException e) {
      e.printStackTrace();
      try {
        shutdownReport();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private class LogReporter implements Runnable {
    private final BufferedReader br;
    private boolean continueReport = true;

    public LogReporter(BufferedReader br) {
      this.br = br;
    }

    public void setContinueReport(boolean continueReport) {
      this.continueReport = continueReport;
    }

    @Override
    public void run() {
      String line;
      while (continueReport) {
        try {
          if ((line = br.readLine()) == null) break;
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        sendMessage(line);
      }
    }
  }
}
