package deadlock;

import logger.ConsoleLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListShifter {

    private static List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
    private static List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6));

    public static void startWithDeadLock() {
        Thread thread1 = new Thread(() ->
                moveItemFromListWithDeadLock(list1, list2, 3, "List1", "List2"));
        Thread thread2 = new Thread(() ->
                moveItemFromListWithDeadLock(list2, list1, 4, "List2", "List1"));

        thread1.start();
        thread2.start();
    }
    
    public static void startWithoutDeadLock() {
        Thread thread1 = new Thread(() ->
                moveItemFromListWithoutDeadLock(list1, list2, 1, "List1", "List2"));
        Thread thread2 = new Thread(() ->
                moveItemFromListWithoutDeadLock(list2, list1, 6, "List2", "List1"));

        thread1.start();
        thread2.start();
    }

    private static void moveItemFromListWithDeadLock(List<Integer> from, List<Integer> to, Integer item,
                                                     String nameListFrom, String nameListTo) {

        ConsoleLogger.log("Пытается заблокировать ", nameListFrom, from);
        synchronized (from) {
            ConsoleLogger.log("Успешно заблокировал ", nameListFrom, from);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ConsoleLogger.log("Пытается заблокировать ", nameListTo, to);
            synchronized (to) {
                ConsoleLogger.log("Успешно заблокировал ", nameListTo, to);

                if (from.remove(item)) {
                    ConsoleLogger.log("Число " + item + " успешно удалено из ", nameListFrom, from);
                    to.add(item);
                    ConsoleLogger.log("Число " + item + " успешно перенесено в ", nameListTo, to);
                }
            }
        }
    }

    private static void moveItemFromListWithoutDeadLock(List<Integer> from, List<Integer> to, Integer item,
                                                        String nameListFrom, String nameListTo) {

        boolean semafor;    // Вводим семафор для обхода взаимной блокировки

        ConsoleLogger.log("Пытается заблокировать ", nameListFrom, from);
        synchronized (from) {
            ConsoleLogger.log("Успешно заблокировал ", nameListFrom, from);
            semafor = from.remove(item);
        }

        if (semafor) {
            ConsoleLogger.log("Число " + item + " успешно удалено из ", nameListFrom, from);
            ConsoleLogger.log("Пытается заблокировать ", nameListTo, to);
            synchronized (to) {
                ConsoleLogger.log("Успешно заблокировал ", nameListTo, to);
                to.add(item);
                ConsoleLogger.log("Число " + item + " успешно перенесено в ", nameListTo, to);
            }
        }


    }
}
