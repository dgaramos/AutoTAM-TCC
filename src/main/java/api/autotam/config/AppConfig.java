package api.autotam.config;


//import api.autotam.config.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;


@Configuration
//@Import({ SecurityConfig.class })
@ComponentScan(basePackages = "api.autotam")
public class AppConfig {
}