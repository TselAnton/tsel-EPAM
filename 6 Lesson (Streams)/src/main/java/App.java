import controller.CommandController;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static CommandController commandController = new CommandController();

    public static void main(String[] args) {
        System.out.println("Консоль запущена!");
        System.out.println("Введите 'help' чтобы увидеть список команд");
        while (true) {
            System.out.print("> ");
            String arguments = scanner.nextLine();
            commandController.getCommand(arguments);
        }
    }
}
