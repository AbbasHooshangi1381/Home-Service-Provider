package question2;

import java.util.TreeSet;

public class Main4 {
    public static void main(String[] args) {
        TreeSet<Character> set1 = new TreeSet<>();
        TreeSet<Character> set2 = new TreeSet<>();

        // پر کردن set1 و set2

        TreeSet<Character> unionSet = findUnion(set1, set2);
        System.out.println("Union: " + unionSet);
    }

    private static TreeSet<Character> findUnion(TreeSet<Character> set1, TreeSet<Character> set2) {
        TreeSet<Character> unionSet = new TreeSet<>(set1);
        unionSet.addAll(set2);
        return unionSet;
    }
}
