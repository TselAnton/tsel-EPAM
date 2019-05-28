package configurate;

import controller.ConnectionController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

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

    @Bean
    public ConnectionController connectionController() {
        return new ConnectionController(url, login, password, jdbcDriver);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
