package utilities;

import org.slf4j.Logger;

/**
 * Доп. функции для логгера
 */
public class LoggerUtil {

    /**
     * Запись ошибки со стак трейсом в лог
     * @param e Ошибка
     * @param logger Логгер
     */
    public static void logExceptions(Exception e, Logger logger) {
        String errMessage = null;
        for (int i = 0; i < e.getStackTrace().length; i++) {
            errMessage += e.getStackTrace()[i].toString() + "\n";
        }
        logger.error("Ошибка (" + e.getMessage() + "): \n" + errMessage);
    }
}
