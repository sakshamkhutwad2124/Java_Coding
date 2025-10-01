// BankAccount.java

class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public double checkBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance, double interestRate) {
        super(accountNumber, accountHolderName, initialBalance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest); // Adds interest to the balance
        System.out.println("Interest applied: " + interest + ". New balance: " + balance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double rate) {
        if (rate > 0) {
            interestRate = rate;
        } else {
            System.out.println("Interest rate must be positive.");
        }
    }
}

// Main.java
public class Assignment_3_Banking_System {

    public static void main(String[] args) {
        // Regular bank account
        BankAccount acc1 = new BankAccount("100", "Saksham", 1000);
        acc1.deposit(700);
        acc1.withdraw(400);

        // Savings account with interest
        SavingsAccount savAcc = new SavingsAccount("101", "Rohan", 2000, 4.0);
        savAcc.deposit(5000);
        savAcc.withdraw(500);
        savAcc.applyInterest();
    }
}
