package question3;

import java.util.ArrayList;

public class QMain6 {

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

        }
    }
