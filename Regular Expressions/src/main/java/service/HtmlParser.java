package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер HTML страницы
 */
public class HtmlParser {
    /**
     * Ссылка на html страницы
     */
    private final String LINK_TO_HTML = "https://www.cbr.ru/currency_base/daily/";

    /**
     * Парсирует заданный html
     * @return Курс доллара в рублях на сегодняшний день
     */
    public double parce() {
        double result = 0;
        try {
            URL url = new URL(LINK_TO_HTML);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder html = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                html.append(line);
            }

            // Здесь всего 5 групп: общая строка, и каждое совпадение в скобках.
            // Мне нужно только 4-ая группа — само значение.
            Pattern pattern = Pattern.compile("(<td>USD</td>.+?)(\\d+)(.+?)(\\d+.\\d+)(.+?</tr>)");
            Matcher matcher = pattern.matcher(html.toString());

            if (matcher.find(4)) {
                result = Double.valueOf(matcher.group(4).replaceAll(",", "."));     // Перевожу число сразу в double
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // На всякий ловлю эксепшен, чтобы программа не падала при изменении html кода
            result = 0;
            e.printStackTrace();
        }
        return result;
    }
}
