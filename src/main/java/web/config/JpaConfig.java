package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfig {
    private Environment en;

    public JpaConfig(Environment en) {
        this.en = en;
    }

    /*
    пул потоков
     */
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(en.getProperty("db.driver"));
        dataSource.setUrl(en.getProperty("db.url"));
        dataSource.setUsername(en.getProperty("db.username"));
        dataSource.setPassword(en.getProperty("db.password"));
        return dataSource;
    }

    /*
    настройка hibernate
     */
    private Properties getPropertyHibernate() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl", en.getProperty("hibernate.hbm2ddl"));
        properties.setProperty("hibernate.show_sql", en.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", en.getProperty("hibernate.dialect"));
        return properties;
    }

    /*
    настройка jpa
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean EntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan("web.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(getPropertyHibernate());
        return entityManagerFactoryBean;
    }

    /*
    не нужно открывать и закрывать транзакции
    активирует @Transactional
     */
    @Bean
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
