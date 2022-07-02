package tk.smileyik.quickpost.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tk.smileyik.quickpost.config.AuthConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 14:47
 */
@Component("restInterceptor")
public class RestInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {
    response.setHeader("Access-Control-Allow-Origin", "*");
    return true;
  }
}
