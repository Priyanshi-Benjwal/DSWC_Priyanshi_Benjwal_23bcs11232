package Day3.Q3;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// Order Class
class Order {
    private String orderId;
    private String traderName;
    private double price;
    private int quantity;

    public Order(String orderId, String traderName,
                 double price, int quantity) {
        this.orderId = orderId;
        this.traderName = traderName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", traderName='" + traderName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

// Exchange Manager
class ExchangeManager {

    // Thread-safe Map
    private ConcurrentHashMap<String,
            CopyOnWriteArrayList<Order>> orderBook;

    public ExchangeManager() {
        orderBook = new ConcurrentHashMap<>();
    }

    // Thread-safe order placement
    public void placeOrder(String ticker, Order order) {

        orderBook
                .computeIfAbsent(
                        ticker,
                        k -> new CopyOnWriteArrayList<>()
                )
                .add(order);
    }

    // Display orders for a ticker
    public void displayOrders(String ticker) {

        List<Order> orders = orderBook.get(ticker);

        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders found for " + ticker);
            return;
        }

        System.out.println("\nOrder Book for " + ticker + ":");

        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

// Driver Class
public class Main {

    public static void main(String[] args) {

        ExchangeManager exchange = new ExchangeManager();

        exchange.placeOrder(
                "BTC",
                new Order("O101", "Alice", 65000.50, 2)
        );

        exchange.placeOrder(
                "BTC",
                new Order("O102", "Bob", 65100.75, 1)
        );

        exchange.placeOrder(
                "ETH",
                new Order("O103", "Charlie", 3500.25, 5)
        );

        exchange.placeOrder(
                "BTC",
                new Order("O104", "David", 65250.00, 3)
        );

        exchange.displayOrders("BTC");
        exchange.displayOrders("ETH");
    }
}
