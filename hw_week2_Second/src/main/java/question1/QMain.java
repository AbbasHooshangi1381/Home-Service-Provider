package question1;

import java.util.HashMap;

public class QMain {

    public static HashMap<String, Integer> countPermutations(String word) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (i == j) continue;
                for (int k = 0; k < word.length(); k++) {
                    if (k == i || k == j) continue;
                    String permutation = "" + word.charAt(i) + word.charAt(j) + word.charAt(k);
                    if (map.containsKey(permutation)) {
                        map.put(permutation, map.get(permutation) + 1);
                    } else {
                        map.put(permutation, 1);
                    }
                }
            }
        }
        return map;
    }
}
