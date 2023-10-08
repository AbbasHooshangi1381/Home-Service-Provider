package question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
        public static void main(String[] args) {
            String word = "CAT";
            List<String> permutations = generatePermutations(word);
            for (String permutation : permutations) {
                System.out.println(permutation);
            }
        }

        private static List<String> generatePermutations(String word) {
            List<String> permutations = new ArrayList<>();
            Map<Character, Integer> charCount = new HashMap<>();
            for (char c : word.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
            generatePermutationsHelper(word.length(), charCount, "", permutations);
            return permutations;
        }

        private static void generatePermutationsHelper(int remaining, Map<Character, Integer> charCount, String current,
                                                       List<String> permutations) {
            if (remaining == 0) {
                permutations.add(current);
                return;
            }
            for (char c : charCount.keySet()) {
                int count = charCount.get(c);
                if (count > 0) {
                    charCount.put(c, count - 1);
                    generatePermutationsHelper(remaining - 1, charCount, current + c, permutations);
                    charCount.put(c, count);
                }
            }
        }
    }


