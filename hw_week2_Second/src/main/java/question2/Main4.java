package question2;

import org.jetbrains.annotations.NotNull;

import java.util.TreeSet;

public class Main4 {
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


        TreeSet<Character> union = findUnion(set1, set2);
        System.out.println("Union: " + union);
    }

    private static TreeSet<Character> findUnion(TreeSet<Character> set1, TreeSet<Character> set2) {
        TreeSet<Character> unionSet = new TreeSet<>(set1);
        unionSet.addAll(set2);
        return unionSet;
    }
}
