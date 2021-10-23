package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean divisibleBy7 = false;
        boolean endsWith7 = false;
        boolean even = false;
        boolean buzz = false;
        boolean duck = false;
        boolean palindromic = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.\n");

        while (true) {

            divisibleBy7 = false;
            endsWith7 = false;
            even = false;
            buzz = false;
            duck = false;
            palindromic = false;

            System.out.print("Enter a request: ");
            long n = scanner.nextLong();
            System.out.println();

            // Only a natural number passes through it.
            if (n < 0) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            } else if (n == 0) {
                System.out.println("Goodbye!");
                break;
            }

            // decide if it is even or odd
            if (n % 2 == 0) {
                even = true;
            }

            // decide if it is buzz
            if (n % 7 == 0) {
                divisibleBy7 = true;
            }
            double d = n % 10;
            if (d == 7) {
                endsWith7 = true;
            }
            if (divisibleBy7 || endsWith7) {
                buzz = true;
            }

            // decide if it is a duck number.
            String stringNumber = "" + n;
            if (stringNumber.contains("0")) {
                duck = true;
            }

            //decide if it is a palindromic number.
            String reversedNumber = "";
            char[] charNumber = stringNumber.toCharArray();
            for (char c : charNumber) {
                reversedNumber = c + reversedNumber;
            }
            if (stringNumber.equals(reversedNumber)) {
                palindromic = true;
            }

            System.out.println("Properties of " + n);
            System.out.println("even: " + even);
            System.out.println("odd: " + !even);
            System.out.println("buzz: " + buzz);
            System.out.println("duck: " + duck);
            System.out.println("palindromic: " + palindromic);
        }
    }
}
