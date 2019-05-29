import configurate.AppConfiguration;
import controller.SessionController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        SessionController sessionController = context.getBean(SessionController.class);
        sessionController.runApp();
    }
}
