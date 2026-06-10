package Day3.Q5;
import java.util.Arrays;
import java.util.List;

// Transaction Status Enum
enum Status {
    COMPLETED,
    PENDING,
    CANCELLED
}

// Product Category Enum
enum Category {
    ELECTRONICS,
    FASHION,
    GROCERY,
    BOOKS
}

// Transaction Class
class Transaction {
    private String transactionId;
    private Category category;
    private Status status;
    private double amount;

    public Transaction(String transactionId,
                       Category category,
                       Status status,
                       double amount) {
        this.transactionId = transactionId;
        this.category = category;
        this.status = status;
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}

// Sales Analyzer
class SalesAnalyzer {

    public double calculateElectronicsRevenue(
            List<Transaction> transactions) {

        return transactions.stream()
                .filter(t -> t.getStatus() == Status.COMPLETED)
                .filter(t -> t.getCategory() == Category.ELECTRONICS)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}

// Driver Class
public class Main {

    public static void main(String[] args) {

        List<Transaction> transactions = Arrays.asList(

                new Transaction(
                        "T101",
                        Category.ELECTRONICS,
                        Status.COMPLETED,
                        50000.0),

                new Transaction(
                        "T102",
                        Category.FASHION,
                        Status.COMPLETED,
                        2000.0),

                new Transaction(
                        "T103",
                        Category.ELECTRONICS,
                        Status.PENDING,
                        15000.0),

                new Transaction(
                        "T104",
                        Category.ELECTRONICS,
                        Status.COMPLETED,
                        30000.0),

                new Transaction(
                        "T105",
                        Category.BOOKS,
                        Status.COMPLETED,
                        1000.0)
        );

        SalesAnalyzer analyzer = new SalesAnalyzer();

        double revenue =
                analyzer.calculateElectronicsRevenue(transactions);

        System.out.println(
                "Electronics Revenue (Completed Transactions): ₹"
                        + revenue);
    }
}
