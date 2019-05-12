import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);

        List<Integer> copy = new ArrayList<>(list);
        for (int i = 0; i < copy.size(); i++) {
            System.out.println(copy.get(i));
        }
    }
}
