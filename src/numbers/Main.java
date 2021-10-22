package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean natural = false;
        boolean divisibleBy7 = false;
        boolean endsWith7 = false;
        String buzz = "It is a Buzz number.";
        String exp = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        int n = scanner.nextInt();

        if (n <= 0) {
            exp = "This number is not natural!";
        } else {
            natural = true;
        }

        if (natural) {
            if (n % 2 == 0) {
                System.out.println("This number is Even.");
            } else {
                System.out.println("This number is Odd.");
            }

            if (n % 7 == 0) {
                divisibleBy7 = true;
            }

            int d = n % 10;
            if (d == 7) {
                endsWith7 = true;
            }

            if (divisibleBy7 && endsWith7) {
                exp = n + " is divisible by 7 and ends with 7.";
            } else if (divisibleBy7) {
                exp = n + " is divisible by 7.";
            } else if (endsWith7) {
                exp = n + " ends with 7.";
            } else {
                buzz = "It is not a Buzz number.";
                exp = n + " is neither divisible by 7 nor does it end with 7.";
            }
        }
        System.out.println(buzz);
        System.out.println("Explanation:");
        System.out.println(exp);
    }
}
