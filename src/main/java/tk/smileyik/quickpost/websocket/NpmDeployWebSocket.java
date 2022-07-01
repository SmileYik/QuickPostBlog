package tk.smileyik.quickpost.websocket;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.smileyik.quickpost.config.AuthConfiguration;
import tk.smileyik.quickpost.config.ExecConfiguration;
import tk.smileyik.quickpost.config.GitConfiguration;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 16:39
 */
@Component
@ServerEndpoint("/{token}")
public class NpmDeployWebSocket {
  private AuthConfiguration authConfiguration;
  private ExecConfiguration execConfiguration;
  private GitConfiguration  gitConfiguration;
  private Session session;
  private Process exec;
  private BufferedReader info;
  private BufferedReader error;
  private Thread infoThread;
  private Thread errorThread;
  private LogReporter infoReporter;
  private LogReporter errorReporter;


  @Autowired
  public void setAuthConfiguration(AuthConfiguration authConfiguration) {
    this.authConfiguration = authConfiguration;
  }

  @Autowired
  public void setExecConfiguration(ExecConfiguration execConfiguration) {
    this.execConfiguration = execConfiguration;
  }

  @Autowired
  public void setGitConfiguration(GitConfiguration gitConfiguration) {
    this.gitConfiguration = gitConfiguration;
  }

  @OnOpen
  public void onOpen(Session session, @PathParam("token") String token) throws IOException {
    if (!authConfiguration.getAdminToken().equals(token)) {
      session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "i can not accept it!!!!"));
      System.out.println("failed!");
      return;
    }
    this.session = session;
    exec = Runtime.getRuntime().exec(
        execConfiguration.getStartCommand(),
        null,
        new File(gitConfiguration.getRepository())
    );
    OutputStream outputStream = exec.getOutputStream();
    outputStream.write(execConfiguration.getNpmDeployCommand().getBytes(StandardCharsets.UTF_8));
    outputStream.flush();
    outputStream.close();
    info = new BufferedReader(new InputStreamReader(exec.getInputStream(), StandardCharsets.UTF_8));
    error = new BufferedReader(new InputStreamReader(exec.getErrorStream(), StandardCharsets.UTF_8));
    infoReporter = new LogReporter(info);
    errorReporter = new LogReporter(error);
    infoThread = new Thread(infoReporter);
    errorThread = new Thread(errorReporter);
    infoThread.start();
    errorThread.start();
    System.out.println("connect!");
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

  private void shutdownReport() throws IOException {
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
    session.getAsyncRemote().sendText(text);
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
