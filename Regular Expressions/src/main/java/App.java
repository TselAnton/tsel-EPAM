import service.HtmlParser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Main Class
 */
public class App {

    // Решил поиграться с DecimalFormat и SimpleDateFormat сразу в main
    static public void main(String[] args) {

        Locale ru = new Locale("ru", "RU");     // Задаю русскую локаль

        HtmlParser htmlParser = new HtmlParser();
        double value = htmlParser.parce();      // Достаю текущий курс доллара

        String decPattern = "###,###.## руб";     // Паттерн для DecimalFormat
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(ru);  // Привязываем вывод к локале
        df.applyPattern(decPattern);    // Задаём паттерн

        String datePattern = "d MMMM y";    // Патерн для SimpleDateFormat
        Date today = new Date();    // Использую Date, потому что SimpleDateFormat не работает с LocalDate

        System.out.println("Курс доллара на " + new SimpleDateFormat(datePattern, ru).format(today) +
                " составляет: " + df.format(value));
    }
}
