package tk.smileyik.quickpost.util;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 10:36
 */
public class Result <T> {
  private boolean success = true;
  private int code = 200;
  private String msg = "";
  private final long timestamp = System.currentTimeMillis();
  private T result;

  public Result(boolean success, int code, String msg, T result) {
    this.success = success;
    this.code = code;
    this.msg = msg;
    this.result = result;
  }

  public Result(boolean success, int code, String msg) {
    this.success = success;
    this.code = code;
    this.msg = msg;
    this.result = (T) msg;
  }

  public Result(String msg, T result) {
    this.msg = msg;
    this.result = result;
  }

  public Result(T result) {
    this.msg = "success";
    this.result = result;
  }

  public boolean isSuccess() {
    return success;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public T getResult() {
    return result;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
