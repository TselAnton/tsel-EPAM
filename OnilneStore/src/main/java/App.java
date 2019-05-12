import controller.SessionController;
import view.impl.extend.SingInView;

public class App {

    public static void main(String[] args) {

//        while (true) {
//            SingInView view = new SingInView();
//            String[] p = view.logIn();
//            System.out.println("p = " + p[0] + " " + p[1]);
//        }

        SessionController sessionController = SessionController.getInstance();
        sessionController.runApp();
    }
}
