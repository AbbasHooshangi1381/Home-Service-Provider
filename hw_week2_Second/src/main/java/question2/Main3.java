package question2;

import java.util.Random;
import java.util.TreeSet;

public class Main3 {
    public static void main(String[] args) {
        TreeSet<Character> set1 = new TreeSet<>();
        TreeSet<Character> set2 = new TreeSet<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            set1.add(c);
        }

        for (int i = 0; i < 10; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            set2.add(c);
        }

        System.out.println("Set 1: " + set1);

        System.out.println("Set 2: " + set2);
    }
}
