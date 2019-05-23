package logger;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleLogger{

    public static void log(String msg, String listName,  List<Integer> list) {
        System.out.println(Thread.currentThread().getName() + ": " +
                msg + " — " + listName + " [" + String.join(", ",
                list.stream().map(Object::toString)
                        .collect(Collectors.toList())) + "]");
    }

    public static void log(String msg) {

        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
