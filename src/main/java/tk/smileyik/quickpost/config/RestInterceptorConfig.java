package tk.smileyik.quickpost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.smileyik.quickpost.config.interceptor.AuthInterceptor;
import tk.smileyik.quickpost.config.interceptor.RestInterceptor;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 14:54
 */
@Configuration
public class RestInterceptorConfig implements WebMvcConfigurer {

  private final RestInterceptor restInterceptor;

  @Autowired
  public RestInterceptorConfig(RestInterceptor restInterceptor) {
    this.restInterceptor = restInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(restInterceptor).addPathPatterns("/**");
  }
}
