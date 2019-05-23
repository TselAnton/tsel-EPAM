package pool;

import entity.Item;
import logger.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsPool {

    private List<Item> itemList;

    private static ItemsPool singleton = null;

    private final int SLEEP = 400;

    private ItemsPool(int numOfItems) {
        itemList = new ArrayList<>();
        for (int i = 0; i < numOfItems; i++) {
            itemList.add(new Item(i+1));
        }
    }

    private ItemsPool() {
    }

    public static ItemsPool getInstance() {
        if (singleton == null) singleton = new ItemsPool(3);
        return singleton;
    }


    public static ItemsPool getInstance(int numOfItems) {
        if (singleton == null) singleton = new ItemsPool(numOfItems);
        return singleton;
    }


    /**
     * Метод работы с одинм Item
     */
    public void useItem() {
        int randomId = new Random().nextInt(itemList.size());

        // Синхронизую именно отдельный Item, чтобы к 1 Item был доступ только у 1 потока
        synchronized (itemList.get(randomId)) {
            itemList.get(randomId).setUsing(true);
            ConsoleLogger.log("Работает с " + itemList.get(randomId).toString());
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ConsoleLogger.log("Закончил работу с " + itemList.get(randomId).toString());
            itemList.get(randomId).setUsing(false);
        }
    }
}
