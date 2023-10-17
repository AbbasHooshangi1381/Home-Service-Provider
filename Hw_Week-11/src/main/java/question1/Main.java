package question1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Integer> evenNumbers = new ArrayList<>();
    private static List<Integer> oddNumbers = new ArrayList<>();
    private static List<Integer> allNumber = new ArrayList<>();
    private static int inputNumber;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        inputNumber = scanner.nextInt();

        Thread evenThread = new Thread(() -> {
            for (int i = 0; i <= inputNumber; i += 2) {
                synchronized (evenNumbers) {
                    evenNumbers.add(i);
                    evenNumbers.notify();
                }
            }
        });

        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= inputNumber; i += 2) {
                synchronized (oddNumbers) {
                    oddNumbers.add(i);
                    oddNumbers.notify();

                }
            }
        });

        Thread allThread = new Thread(() -> {
            for (int i = 0; i <= inputNumber; i ++) {
                synchronized (allNumber) {
                    allNumber.add(i);
                    allNumber.notify();
                }
            }
        });

        evenThread.start();
        oddThread.start();
        allThread.start();

        try {
            evenThread.join();
            oddThread.join();
            allThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("List of even numbers: " + evenNumbers);
        System.out.println("List of odd numbers: " + oddNumbers);
        System.out.println("List of all numbers: " + allNumber);
    }
}