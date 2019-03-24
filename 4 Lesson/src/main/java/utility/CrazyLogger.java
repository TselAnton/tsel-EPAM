package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyLogger {
    private static CrazyLogger singleton = null;
    private final StringBuilder logger = new StringBuilder();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY:hh-mm"); // Форматированный вид

    private CrazyLogger() {}

    /**
     * Singleton
     * Решил реализовать singleton, так как logger по логике должен быть один на приложение
     * @return  singleton
     */
    public static CrazyLogger getInstanse() {
        if (singleton == null) singleton = new CrazyLogger();
        return singleton;
    }

    /**
     * Add message
     * @param message Message
     */
    public void addMessage(String message) {
        logger.append(formatter.format(LocalDateTime.now()) + " - " + message + "\n");
    }

    /**
     * Show all message of logger
     */
    public void showAllLog() {
        int first = 0, second = logger.indexOf("\n");
        if (second == -1) {
            System.out.println("Logger is empty!");
        } else {
            System.out.println("All content of CrazyLogger:");
            while (second != -1) {
                System.out.println(logger.substring(first, second));
                first = second + 1;
                second = logger.indexOf("\n", first);
            }
        }
    }

    /**
     * Logger Message Search
     * @param message Message
     */
    public void findMessage(String message) {
        int first = 0, second = logger.indexOf("\n");

        if (second == -1) {
            System.out.println("Logger is empty!");
        } else {
            System.out.println("All occurrences of the message '" + message + "':");
            boolean check = false;  // Проверка наличие вхождений
            while (second != -1) {
                if (logger.substring(first, second + 1).contains(message)) {
                    System.out.println(logger.substring(first, second));
                    if (!check) {
                        check = true;
                    }
                }
                first = second + 1;
                second = logger.indexOf("\n", first);
            }
            if (!check) {
                System.out.println("Not one entry!");
            }
        }
    }
}
