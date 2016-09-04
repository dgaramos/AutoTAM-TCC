package api.autotam.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
//@Import({ SecurityConfig.class })
@ComponentScan(basePackages = "api.autotam")
public class AppConfig {
}