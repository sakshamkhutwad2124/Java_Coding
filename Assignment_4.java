
import java.util.*;

class Account {

    private static int nextAccountNumber = 1001;
    private int accountNumber;
    private String accountHolderName;
    private int pin;
    private double balance;

    public Account(String name, int pin, double initialBalance) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolderName = name;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void checkBalance() {
        System.out.println("Current Balance Rs: " + balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposited amount is INVALID!");
        }
        balance += amount;
        System.out.println("Successfully deposited Rs: " + amount);
        checkBalance();
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount is INVALID!");
        }
        if (amount > balance) {
            throw new ArithmeticException("Insufficient Balance.");
        }
        balance -= amount;
        System.out.println("Successfully withdrawn Rs: " + amount);
        checkBalance();
    }
}

public class Assignment_4_ATM_Stimulator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        System.out.println("Welcome to K.J BANK ATM");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create New Account");
            System.out.println("2. Access Existing Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice;
            try {
                mainChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                sc.next();
                continue;
            }

            switch (mainChoice) {
                case 1:

                    sc.nextLine();
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Set your 4-digit PIN: ");
                    int pin = sc.nextInt();
                    Account newAccount = new Account(name, pin, 0.0);
                    accounts.add(newAccount);
                    System.out.println("Account created successfully!");
                    System.out.println("Account Holder: " + newAccount.getAccountHolderName());
                    System.out.println("Account Number: " + newAccount.getAccountNumber());
                    break;

                case 2:

                    System.out.print("Enter Account Number: ");
                    int accNum = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int enteredPin = sc.nextInt();

                    Account acc = null;
                    for (Account a : accounts) {
                        if (a.getAccountNumber() == accNum && a.verifyPin(enteredPin)) {
                            acc = a;
                            break;
                        }
                    }

                    if (acc == null) {
                        System.out.println("Invalid account number or PIN.");
                        break;
                    }

                    while (true) {
                        System.out.println("\nWelcome " + acc.getAccountHolderName());
                        System.out.println("1. Check Balance");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdraw");
                        System.out.println("4. Logout");
                        System.out.print("Enter your choice: ");

                        int choice;
                        try {
                            choice = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter numbers only.");
                            sc.next();
                            continue;
                        }

                        try {
                            switch (choice) {
                                case 1:
                                    acc.checkBalance();
                                    break;
                                case 2:
                                    System.out.print("Enter deposit amount: ");
                                    double depositAmount = sc.nextDouble();
                                    acc.deposit(depositAmount);
                                    break;
                                case 3:
                                    System.out.print("Enter withdrawal amount: ");
                                    double withdrawAmount = sc.nextDouble();
                                    acc.withdraw(withdrawAmount);
                                    break;
                                case 4:
                                    System.out.println("Logged out successfully.");
                                    throw new Exception("logout");
                                default:
                                    System.out.println("Invalid choice! Please choose between 1-4.");
                            }
                        } catch (IllegalArgumentException | ArithmeticException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        } catch (Exception logout) {
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using ATM Simulator!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please choose between 1-3.");
            }
        }
    }
}
