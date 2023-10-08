package question2;

import java.util.TreeSet;

public class Main5 {
    public static void main(String[] args) {
        TreeSet<Character> set1 = new TreeSet<>();
        TreeSet<Character> set2 = new TreeSet<>();

        // پر کردن set1 و set2

        TreeSet<Character> intersectionSet = findIntersection(set1, set2);
        System.out.println("Intersection: " + intersectionSet);
    }

    private static TreeSet<Character> findIntersection(TreeSet<Character> set1, TreeSet<Character> set2) {
        TreeSet<Character> intersectionSet = new TreeSet<>(set1);
        intersectionSet.retainAll(set2);
        return intersectionSet;
    }
}