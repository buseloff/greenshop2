package com.busel.spring.shop_rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.busel.spring.shop_rest")
@EnableWebMvc
@EnableTransactionManagement
public class Config {
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("C:\\Users\\Alexandr\\Downloads\\backend\\db.properties")) {
            props.load(fis);
            dataSource.setDriverClass(props.getProperty("POSTGRESQL_DB_DRIVER_CLASS"));
            dataSource.setJdbcUrl(props.getProperty("POSTGRESQL_DB_URL"));
            dataSource.setUser(props.getProperty("POSTGRESQL_DB_USERNAME"));
            dataSource.setPassword(props.getProperty("POSTGRESQL_DB_PASSWORD"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.busel.spring.shop_rest.entities");
        Properties hibernateProperties = new Properties();
        Properties props = new Properties();
       try (FileInputStream fis = new FileInputStream("C:\\Users\\Alexandr\\Downloads\\backend\\db.properties")) {
           props.load(fis);
           hibernateProperties.setProperty("hibernate.dialect", props.getProperty("POSTGRESQL_HIBERNATE_DIALECT"));
           hibernateProperties.setProperty("hibernate.show_sql", props.getProperty("POSTGRESQL_CONSOLE_ENABLED"));
           sessionFactory.setHibernateProperties(hibernateProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
