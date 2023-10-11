package question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static question1.QMain.countPermutations;

public class Main {
    public static void main(String[] args) {

        String word = "cat";
        HashMap<String, Integer> map = countPermutations(word);
        System.out.println(map);
    }
}




