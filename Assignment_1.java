
import java.util.Scanner;

public class Assignment_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculation = true;

        while (continueCalculation) {
            double num1 = 0;
            double num2 = 0;
            char operator = ' ';

            // Input: First number with error handling
            while (true) {
                System.out.print("Enter the first number: ");
                try {
                    num1 = Double.parseDouble(scanner.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }

            // Input: Second number with error handling
            while (true) {
                System.out.print("Enter the second number: ");
                try {
                    num2 = Double.parseDouble(scanner.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }

            // Input: Operator with validation
            while (true) {
                System.out.print("Enter an operator (+, -, *, /): ");
                String opInput = scanner.next();
                if (opInput.length() == 1 && "+-*/".contains(opInput)) {
                    operator = opInput.charAt(0);
                    break;
                } else {
                    System.out.println("Invalid operator! Please enter one of +, -, *, /.");
                }
            }

            // Perform calculation with exception handling
            double result = 0;
            boolean validOperation = true;
            try {
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero is not allowed.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        validOperation = false;
                        System.out.println("Unknown operator. This should not occur.");
                }

                if (validOperation) {
                    System.out.printf("Result: %.4f %c %.4f = %.4f%n", num1, operator, num2, result);
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Ask user if they want another calculation
            System.out.print("Do you want to perform another calculation? (y/n): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("y")) {
                continueCalculation = false;
                System.out.println("Exiting calculator. Goodbye!");
            }
        }

        scanner.close();
    }
}
