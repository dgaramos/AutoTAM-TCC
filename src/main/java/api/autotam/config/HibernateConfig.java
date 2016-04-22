package api.autotam.config;

import javax.sql.DataSource;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import api.autotam.service.UsuarioService;
import api.autotam.service.UsuarioServiceImpl;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("api.autotam")
/** Important **/
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    Environment env;



    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        try {
           /* mySQL connection code
           dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/autotam");
            dataSource.setUsername("root");
            dataSource.setPassword("root");*/
            dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
            dataSource.setUrl(env.getProperty("jdbc.databaseurl"));
            dataSource.setUsername(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
        }catch (Exception e){
            System.out.println("Não conectou!");
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean
                .setPackagesToScan(new String[] { "api.autotam.model" });
        factoryBean.setHibernateProperties(getHibernateProperties());

       try {
            factoryBean.afterPropertiesSet();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return factoryBean.getObject();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        //mySQL database dialect
        //properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.dialect", env.getProperty("jdbc.dialect"));
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("current_session_context_class", "thread");
        properties.put("hibernate.hbm2ddl.auto","update");
        return properties;
    }

    @Bean(name = "transactionManager")
   @Autowired
   public HibernateTransactionManager getTransactionManager(SessionFactory s) throws Exception {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }



    @Bean (name= "usuarioService")
    public UsuarioService usuarioService(){ return new UsuarioServiceImpl();}


}
