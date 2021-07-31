package net.vixim.twip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TwipApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(TwipApplication.class, args);
  }

}
