package tk.smileyik.quickpost.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 14:54
 */
@Configuration
public class RestInterceptorConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowCredentials(true)
        .allowedOriginPatterns("*")
        .allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
        .allowedHeaders("*")
        .exposedHeaders("*");
  }
}
