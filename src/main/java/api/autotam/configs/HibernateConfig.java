package api.autotam.configs;

import javax.sql.DataSource;


import api.autotam.services.interfaces.AnaliseService;
import api.autotam.services.implementations.AnaliseServiceImpl;
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
import api.autotam.services.interfaces.UsuarioService;
import api.autotam.services.implementations.UsuarioServiceImpl;

import java.io.IOException;
import java.util.Properties;

/**
 * Essa classe gerencia a conexão com o banco de dados e ORM em relação aos modelos
 * Created by Danilo on 9/4/2016.
 */

@Configuration
@ComponentScan("api.autotam")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        try {
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

    @Bean (name= "analiseService")
    public AnaliseService analiseService(){ return new AnaliseServiceImpl();}


}
