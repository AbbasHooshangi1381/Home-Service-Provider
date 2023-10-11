package question1;

import java.util.HashMap;
import java.util.Map;

public class Qmain2 {

    static boolean checkWordConditions(String word1, String word2) {
        Map<Character, Integer> charCount1 = new HashMap<>();
        Map<Character, Integer> charCount2 = new HashMap<>();


        for (char c : word1.toCharArray()) {
            charCount1.put(c, charCount1.getOrDefault(c, 0) + 1);
        }

        for (char c : word2.toCharArray()) {
            charCount2.put(c, charCount2.getOrDefault(c, 0) + 1);
        }

        if (charCount1.size() != charCount2.size()) {
            return false;
        }

        for (char c : charCount1.keySet()) {
            if (!charCount2.containsKey(c) || charCount1.get(c) != charCount2.get(c)) {
                return false;
            }
        }

        return true;
    }
}
