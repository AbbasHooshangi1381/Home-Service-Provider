
package question3;

import java.util.ArrayList;
import java.util.Arrays;

import static question3.QMain6.removeInappropriatePairs;

public class Main6 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 7, 4, 3, 6, 5, 8, 5, 5, 2, 9, 7, 3));
        removeInappropriatePairs(list);
        System.out.println(list); // [7, 4, 5, 5, 7, 3]
    }

    }
