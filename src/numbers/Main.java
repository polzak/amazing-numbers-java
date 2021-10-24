package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        long n = 0;
        long d = 0;
        String line;
        boolean isMultiple = false;

        Scanner scanner = new Scanner(System.in);
        showInstructions();

        while (true) {
            System.out.print("Enter a request: ");

            line = scanner.nextLine();
            System.out.println();

            if (line.isEmpty()) {
                System.out.println();
                showInstructions();
                continue;
            }

            String[] twoNumbers = line.split(" ");
            isMultiple = twoNumbers.length > 1;

            // check the validity of the numbers.
            try {
                n = Long.parseLong(twoNumbers[0]);
            } catch (NumberFormatException nfe) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            }

            if (isMultiple) {
                try {
                    d = Long.parseLong(twoNumbers[1]);
                } catch (NumberFormatException nfe) {
                    System.out.println("The second parameter should be a natural number.\n");
                    continue;
                }
            }

            if (n < 0) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            } else if (n == 0) {
                System.out.println("Goodbye!");
                break;
            } else if (isMultiple && d <= 0) {
                System.out.println("The second parameter should be a natural number.\n");
                continue;
            }

            // inspect the numbers.
            if (isMultiple) {
                inspectMultipleNumbers(n, d);
            } else {
                NumberInspector ni = new NumberInspector(n);
                ni.showInspections();
            }

        }
    }

    public static void showInstructions() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    public static void inspectMultipleNumbers(long n, long d) {

        String result = "";

        for (long i = 0; i < d;i++) {
            result = String.format("%d is ", n);
            NumberInspector ni = new NumberInspector(n);
            result += ni.isBuzz() ? "buzz, ": "";
            result += ni.isDuck() ? "duck, ": "";
            result += ni.isPalindromic() ? "palindromic, ": "";
            result += ni.isGapful() ? "gapful, ": "";
            result += ni.isEven() ? "even": "odd";
            System.out.println(result);
            n++;
        }
        System.out.println();
    }

    static class NumberInspector {

        long n;
        String stringNumber;
        int stringNumberLength;

        NumberInspector(long n) {
            this.n = n;
            this.stringNumber = "" + n;
            this.stringNumberLength = this.stringNumber.length();
        }

        public boolean isEven() {
            return n % 2 == 0;
        }

        public boolean isGapful() {
            boolean gapful = false;
            long gapfulBase = 0;
            String gapfulBaseString = "";
            if (this.stringNumberLength > 2) {
                gapfulBaseString += this.stringNumber.charAt(0);
                gapfulBaseString += this.stringNumber.charAt(this.stringNumberLength - 1);
                gapfulBase = Long.parseLong(gapfulBaseString);
                if (n % gapfulBase == 0) {
                    gapful = true;
                }
            }
            return gapful;
        }

        public boolean isBuzz() {
            return (n % 7 == 0) || (n % 10) == 7;
        }

        public boolean isDuck() {
            return this.stringNumber.contains("0");
        }

        public boolean isPalindromic() {
            String reversedNumber = "";
            char[] charNumber = this.stringNumber.toCharArray();
            for (char c : charNumber) {
                reversedNumber = c + reversedNumber;
            }
            return this.stringNumber.equals(reversedNumber);
        }

        public void showInspections() {
            System.out.println("Properties of " + n);
            System.out.println("buzz: " + isBuzz());
            System.out.println("duck: " + isDuck());
            System.out.println("palindromic: " + isPalindromic());
            System.out.println("gapful: " + isGapful());
            System.out.println("even: " + isEven());
            System.out.println("odd: " + !isEven());
            System.out.println();
        }
    }

}
