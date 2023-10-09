
package question3;

import java.util.ArrayList;
import java.util.Arrays;

public class Main6 {
        public static void main(String[] args) {
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 7, 4, 3, 6, 5, 8, 5, 5, 2, 9, 7, 3));
            removeInappropriatePairs(list);
            System.out.println(list); // [7, 4, 5, 5, 7, 3]
        }

        public static void removeInappropriatePairs(ArrayList<Integer> list) {
            int size = list.size();
            if (size % 2 != 0) {
                size--;
            }
            for (int i = 0; i < size; i += 2) {
                if (list.get(i) > list.get(i + 1)) {
                    list.remove(i);
                    size -= 2;
                    i -= 2;
                    if (i < -1) {
                        i = -1;
                    }
                }
            }
            if (list.size() % 2 != 0) {
                list.remove(list.size() - 1);
            }
        }
    }
