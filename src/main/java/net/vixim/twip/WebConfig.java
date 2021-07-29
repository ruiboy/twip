package net.vixim.twip;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer
{
  // Disable SOP for some endpoints - clearly a bad idea, but this is a toy and I want
  // to support concurrent tomcat and npm dev on different ports.
  @Override
  public void addCorsMappings(CorsRegistry registry)
  {
    registry.addMapping("/**").allowedOrigins("http://localhost:3000");
  }
}