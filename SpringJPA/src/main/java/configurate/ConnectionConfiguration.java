package configurate;

import controller.ConnectionController;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;


@Configuration
@PropertySource(value = "classpath:database.properties")
public class ConnectionConfiguration {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String login;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.dbName}")
    private String dbName;

    @Value("%{hibernate.dialect}")
    private String dialect;

    @Value("%{hibernate.show_sql}")
    private String showSql;

    @Value("%{hibernate.format_sql}")
    private String formatSql;


    @Bean
    public ConnectionController connectionController() {
        return new ConnectionController(url, login, password, jdbcDriver);
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineStore");
        return emf.createEntityManager();
    }
}
