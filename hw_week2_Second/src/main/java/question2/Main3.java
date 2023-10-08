package question2;

import java.util.Random;
import java.util.TreeSet;

public class Main3 {
    public static void main(String[] args) {
        TreeSet<Character> set1 = new TreeSet<>();
        TreeSet<Character> set2 = new TreeSet<>();

        Random random = new Random();

        // پر کردن set1 با 10 کاراکتر تصادفی
        for (int i = 0; i < 10; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            set1.add(c);
        }

        // پر کردن set2 با 10 کاراکتر تصادفی
        for (int i = 0; i < 10; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            set2.add(c);
        }

        // نمایش محتوای set1
        System.out.println("Set 1: " + set1);

        // نمایش محتوای set2
        System.out.println("Set 2: " + set2);
    }
}
