package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String[] PROPERTIES = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD"};
        final Scanner scanner = new Scanner(System.in);
        showInstructions();

        while (true) {
            long n = 0;
            long d = 0;
            byte requestOption = 1;
            String line = "";
            String property = "";
            boolean hasProperty = false;

            System.out.print("Enter a request: ");

            line = scanner.nextLine();
            System.out.println();

            if (line.isEmpty()) {
                System.out.println();
                showInstructions();
                continue;
            }

            String[] lineArray = line.split(" ");

            // the way to quit.
            if (lineArray[0].equals("0")) {
                System.out.println("Goodbye!");
                break;
            }

            // the user's options.
            switch (lineArray.length) {
                case 1:
                    requestOption = 1;
                    break;
                case 2:
                    requestOption = 2;
                    break;
                case 3:
                    requestOption = 3;
                    break;
            }

            // Convert and check the first number.
            try {
                n = Long.parseLong(lineArray[0]);
            } catch (NumberFormatException nfe) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            }

            if (n < 0) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            }

            // Convert and check the second number.
            if (requestOption > 1) {
                try {
                    d = Long.parseLong(lineArray[1]);
                } catch (NumberFormatException nfe) {
                    System.out.println("The second parameter should be a natural number.\n");
                    continue;
                }
            }

            if (d < 0) {
                System.out.println("The second parameter should be a natural number or zero.\n");
                continue;
            }

            // Check the third parameter, the property.
            if (requestOption == 3) {
                property = lineArray[2].toUpperCase();
                for (String p : PROPERTIES) {
                    if (p.equals(property)) {
                        hasProperty = true;
                        break;
                    }
                }
                if (!hasProperty) {
                    System.out.printf("The property [%s] is wrong.\n", property);
                    System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]\n");
                    continue;
                }
            }

            // inspect the numbers.
            switch (requestOption) {
                case 1:
                    NumberInspector ni = new NumberInspector(n);
                    ni.showInspections();
                    break;
                case 2:
                    inspectMultipleNumbers(n, d);
                    break;
                case 3:
                    getNumbersOfProperty(n, d, property);
                    break;
            }
        }
    }


    public static void showInstructions() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Amazing Numbers!\n\n");
        sb.append("Supported requests:\n");
        sb.append("- enter a natural number to know its properties;\n");
        sb.append("- enter two natural numbers to obtain the properties of the list:\n");
        sb.append("  * the first parameter represents a starting number;\n");
        sb.append("  * the second parameters show how many consecutive numbers are to be processed;\n");
        sb.append("- two natural numbers and a property to search for;\n");
        sb.append("- separate the parameters with one space;\n");
        sb.append("- enter 0 to exit.\n");
        System.out.println(sb.toString());
    }

    public static void getNumbersOfProperty(long n, long d, String property) {
        while (d > 0) {
            boolean isRightNumber = false;
            NumberInspector ni = new NumberInspector(n);

            switch (property) {
                case "BUZZ":
                    isRightNumber = ni.isBuzz();
                    break;
                case "DUCK":
                    isRightNumber = ni.isDuck();
                    break;
                case "PALINDROMIC":
                    isRightNumber = ni.isPalindromic();
                    break;
                case "GAPFUL":
                    isRightNumber = ni.isGapful();
                    break;
                case "SPY":
                    isRightNumber = ni.isSpy();
                    break;
                case "EVEN":
                    isRightNumber = ni.isEven();
                    break;
                case "ODD":
                    isRightNumber = !ni.isEven();
                    break;
            }

            if (isRightNumber) {
                ni.showInOneLine();
                d--;
            }
            n++;
        }
        System.out.println();
    }

    public static void inspectMultipleNumbers(long n, long d) {

        for (long i = 0; i < d;i++) {
            NumberInspector ni = new NumberInspector(n);
            ni.showInOneLine();
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

        public boolean isSpy() {
            long added = 0;
            long multiplied = 1;
            String[] ns = this.stringNumber.split("");
            for (String s: ns) {
                long ls = Long.parseLong(s);
                added += ls;
                multiplied *= ls;
            }
            return added == multiplied;
        }

        public void showInspections() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Properties of %,d:\n", n));
            sb.append(String.format("buzz: %b\n", isBuzz()));
            sb.append(String.format("duck: %b\n", isDuck()));
            sb.append(String.format("palindromic: %b\n", isPalindromic()));
            sb.append(String.format("gapful: %b\n", isGapful()));
            sb.append(String.format("spy: %b\n", isSpy()));
            sb.append(String.format("even: %b\n", isEven()));
            sb.append(String.format("odd: %b\n", !isEven()));
            System.out.println(sb.toString());
        }

        public void showInOneLine() {
            StringBuilder result = new StringBuilder();
            result.append(String.format("%,d", n));
            result.append(" is ");
            if (isBuzz()) { result.append("buzz, "); }
            if (isDuck()) { result.append("duck, "); }
            if (isPalindromic()) { result.append("palindromic, "); }
            if (isGapful()) { result.append("gapful, "); }
            if (isSpy()) { result.append("spy, "); }
            if (isEven()) { result.append("even"); } else { result.append("odd"); }
            System.out.println(result.toString());
        }
    }
}
