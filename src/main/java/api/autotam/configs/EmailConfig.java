package api.autotam.configs;

/**
 * Created by Danilo on 9/8/2016.
 */
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configurable
@PropertySource("classpath:javamail.properties")
public class EmailConfig {

    @Autowired
    Environment env;

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(env.getProperty("gmail.host"));
        mailSender.setPort(587);

        //Set gmail email id
        mailSender.setUsername(env.getProperty("gmail.user"));
        //Set gmail email password
        mailSender.setPassword(env.getProperty("gmail.password"));
        Properties prop = mailSender.getJavaMailProperties();

        prop.put("mail.transport.protocol", env.getProperty("gmail.mail.transport.protocol"));
        prop.put("mail.smtp.auth",env.getProperty("gmail.mail.smtp.auth"));
        prop.put("mail.smtp.starttls.enable",env.getProperty( "gmail.mail.smtp.starttls.enable"));
        prop.put("mail.debug", env.getProperty("gmail.mail.debug"));
        return mailSender;
    }
}