package utils;

import exeptions.NotPointFound;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewUtils {

    public static int getMenuPoint(Scanner scanner, int pointCount, Logger logger, Marker Markaer) {
        System.out.print("Выберете пункт меню: ");
        int point = 0;

        try {
            try {
                point = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (point != 0 && (point > pointCount || point < 1)) {
                    throw new NotPointFound(point);
                }
            }
        } catch (NotPointFound e) {
            logger.error(Markaer, "NotPointFound", e);
            System.out.println("Invalid menu item");
        }

        System.out.println();
        return point;
    }

    public static int inputNumber(Scanner scanner, int sizeOfItem, Logger logger, Marker marker) {
        int numberOfItem = -1;

        try {
            try {
                numberOfItem = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (numberOfItem != -1 && (numberOfItem > sizeOfItem || numberOfItem < 1)) {
                    numberOfItem = 0;
                    throw new NotPointFound(numberOfItem);
                }
            }
        } catch (NotPointFound e) {
            logger.error(marker, String.format("Number entered is out of range: %d [1, %d]", numberOfItem), e);
            System.out.println("Такого товара не существует!");
        }
        return numberOfItem;
    }
}
