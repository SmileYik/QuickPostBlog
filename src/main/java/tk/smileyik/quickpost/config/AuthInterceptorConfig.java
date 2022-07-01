package tk.smileyik.quickpost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.smileyik.quickpost.config.interceptor.AuthInterceptor;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 14:54
 */
@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {

  private final AuthInterceptor authInterceptor;

  @Autowired
  public AuthInterceptorConfig(AuthInterceptor authInterceptor) {
    this.authInterceptor = authInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor).addPathPatterns("/**");
  }
}
