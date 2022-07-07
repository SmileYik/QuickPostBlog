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
@Component("authInterceptor")
public class AuthInterceptor implements HandlerInterceptor {

  private AuthConfiguration authConfiguration;

  @Autowired
  public void setAuthConfiguration(AuthConfiguration authConfiguration) {
    this.authConfiguration = authConfiguration;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {
    if (request.getMethod().equals("OPTIONS")) {
      return true;
    }
    String token = request.getHeader("TOKEN");
    if (token == null) {
      token = request.getParameter("TOKEN");
    }
    boolean flag = authConfiguration.getAdminToken().equals(token);
    if (!flag) {
      response.sendError(404);
    }
    return flag;
  }
}
