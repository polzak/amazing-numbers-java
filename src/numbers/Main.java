package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean natural = false;
        boolean divisibleBy7 = false;
        boolean endsWith7 = false;
        boolean even = false;
        boolean buzz = false;
        boolean duck = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("This number is not natural!");
        } else {
            natural = true;
        }

        if (natural) {
            // decide if it is even or odd
            if (n % 2 == 0) {
                even = true;
            }

            // decide if it is buzz
            if (n % 7 == 0) {
                divisibleBy7 = true;
            }
            int d = n % 10;
            if (d == 7) {
                endsWith7 = true;
            }
            if (divisibleBy7 || endsWith7) {
                buzz = true;
            }

            String stringNumber = "" + n;
            if (stringNumber.contains("0")) {
                duck = true;
            }

            System.out.println("Properties of " + n);
            System.out.println("even: " + even);
            System.out.println("odd: " + !even);
            System.out.println("buzz: " + buzz);
            System.out.println("duck: " + duck);
        }
    }
}
