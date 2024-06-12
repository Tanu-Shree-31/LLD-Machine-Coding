package Driver;

import Entity.Order;
import Entity.Product;
import Service.InventoryManagementSystem;

public class Main {
    /*
    1. adding products
    2. removing products
    3. placing orders
    4. viewing all products
     */
    public static void main(String[] args) {
        InventoryManagementSystem inventoryManagementSystem = new InventoryManagementSystem();

        Product product1 = new Product("P001", "Laptop", "Study purpose",50000.78, 20);
        Product product2 = new Product("P002", "Headphones", "Noise cancellation",1500.50, 2);

        inventoryManagementSystem.addProduct(product1);
        inventoryManagementSystem.addProduct(product2);

        inventoryManagementSystem.viewAllProducts();

        Order order1 = new Order("O001", "P001", 2, "C001");
        inventoryManagementSystem.createOrder(order1);

        // Simulate waiting time of less than 30 seconds
        try {
            Thread.sleep(20000);  // 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inventoryManagementSystem.confirmOrder(order1.getOrderId());
        inventoryManagementSystem.viewAllProducts();

        inventoryManagementSystem.removeProduct("P002");
        inventoryManagementSystem.viewAllProducts();

    }
}