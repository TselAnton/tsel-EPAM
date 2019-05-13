import entity.User;

public class App {
    public static void main(String[] args) {

        User u1 = new User();
        u1.setFio("USER1");

        User u2 = new User(u1);
        System.out.println(u2.getFio());
    }
}
