package configurate;

import controller.ConnectionController;
import controller.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import utils.ViewUtils;


@Import({ConnectionConfiguration.class, ExceptionConfiguration.class, DtoGeneratorsConfiguration.class,
        EntityGeneratorsConfiguration.class, CollectionsGeneratorsConfiguration.class, DtoConfiguration.class,
        DaoConfiguration.class, ServiceConfiguration.class, ViewConfiguration.class, ConvectorsConfiguration.class})
@PropertySource("classpath:database.properties")
@Configuration
@ComponentScan("model")
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
