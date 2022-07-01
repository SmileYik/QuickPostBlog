package tk.smileyik.quickpost.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tk.smileyik.quickpost.config.BlogConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 14:47
 */
@Component("authInterceptor")
public class AuthInterceptor implements HandlerInterceptor {

  private BlogConfiguration blogConfiguration;

  @Autowired
  public void setBlogConfiguration(BlogConfiguration blogConfiguration) {
    this.blogConfiguration = blogConfiguration;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {
    String token = request.getHeader("TOKEN");
    boolean flag = blogConfiguration.getAdminToken().equals(token);
    if (!flag) {
      response.sendError(404);
    }
    return flag;
  }
}
