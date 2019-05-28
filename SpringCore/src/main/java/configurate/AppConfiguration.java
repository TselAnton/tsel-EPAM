package configurate;

import controller.ConnectionController;
import controller.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import utils.ViewUtils;


@Import({ConnectionConfiguration.class, ExceptionConfiguration.class, DtoGeneratorsConfiguration.class,
        EntityGeneratorsConfiguration.class, CollectionsGeneratorsConfiguration.class, ModelConfiguration.class,
        DtoConfiguration.class, DaoConfiguration.class, ServiceConfiguration.class, ViewConfiguration.class,
        ConvectorsConfiguration.class})
@PropertySource("classpath:database.properties")
@Configuration
public class AppConfiguration {

    @Autowired
    private ConnectionController connectionController;

    @Bean
    public SessionController sessionController() {
        return new SessionController(connectionController);
    }

    @Bean
    public ViewUtils viewUtils() {
        return new ViewUtils();
    }
}
