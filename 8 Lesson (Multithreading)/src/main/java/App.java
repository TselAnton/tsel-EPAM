import deadlock.ListShifter;
import pool.ItemsPool;

public class App {

    public static void main(String[] args) {

        System.out.println("DeadLock");
        ListShifter.startWithDeadLock();   // Закомментить, чтобы обойти

        System.out.println("Обход DeadLock");
        ListShifter.startWithoutDeadLock();

        System.out.println("Синхронная работа с пулом объектов");
        ItemsPool itemsPool = ItemsPool.getInstance(3);     // Создаём заведомо меньше Item'ов для большего кол-ва потоков
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(itemsPool::useItem);
            thread.start();
        }

    }
}
