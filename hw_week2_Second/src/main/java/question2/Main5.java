package question2;

import java.util.TreeSet;

public class Main5 {
    public static void main(String[] args) {
        TreeSet<Character> set1 = new TreeSet<>();
        TreeSet<Character> set2 = new TreeSet<>();

        set1.add('a');
        set1.add('c');
        set1.add('m');
        set1.add('2');

        set2.add('c');
        set2.add('2');
        set2.add('6');
        set2.add('m');


        TreeSet<Character> Subscription = findSubscription(set1, set2);
        System.out.println("Subscription: " + Subscription);
    }

    private static TreeSet<Character> findSubscription(TreeSet<Character> set1, TreeSet<Character> set2) {
        TreeSet<Character> Subscription = new TreeSet<>(set1);
        Subscription.retainAll(set2);
        return Subscription;
    }
}