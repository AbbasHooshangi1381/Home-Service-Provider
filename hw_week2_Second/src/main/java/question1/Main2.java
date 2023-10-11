package question1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the first word: ");
                String word1 = scanner.nextLine();
                System.out.print("Enter the second word: ");
                String word2 = scanner.nextLine();

                boolean isPass = Qmain2.checkWordConditions(word1, word2);
                if (isPass) {
                    System.out.println("Pass");
                } else {
                    System.out.println("Fail");
                }
        }
    }