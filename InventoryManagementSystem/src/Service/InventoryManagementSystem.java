package Service;

import Entity.Inventory;
import Entity.Order;
import Entity.Product;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/*
This class manages the overall system,
including creating products, managing orders, and maintaining the inventory.
 */
public class InventoryManagementSystem {
    private Inventory inventory;
    private Map<String, Order> orderMap;
    private Map<String, Order> cart;
    public InventoryManagementSystem(){
        inventory=new Inventory();
        orderMap = new HashMap<>();
        cart = new HashMap<>();
    }

    // 1. add a product
    public void addProduct(Product product){
        inventory.addProduct(product);
        System.out.println("Entity.Product added: "+product.getName());
    }


    // 2. remove a product
    public void removeProduct(String productId){
        inventory.removeProduct(productId);
        System.out.println("Entity.Product removed: "+productId);
    }

    // 3. view all products
    public void viewAllProducts(){
        Map<String, Product> product = inventory.getAllProducts();
        for(Product i: product.values()){
            System.out.println("Entity.Product ID: "+i.getProductId()+" Name: "+i.getName()+" Quantity: "+i.getQuantity());
        }
    }

    // 4. get total inventory value of the warehouse
    public double getTotalInventoryValue(){
        return inventory.getTotalInventoryValue();
    }


    // 5. place/create the order
    public void createOrder(Order order){
         cart.put(order.getOrderId(), order);
         System.out.println("Entity.Order created: "+order.getOrderId());
    }

    // 6. confirm the order
    public void confirmOrder(String orderId){
       Order order = cart.get(orderId);
       if(order!=null){
           LocalDateTime now = LocalDateTime.now();
           Duration duration = Duration.between(order.getCreateTime(),now);
           if(duration.getSeconds()>30){
               System.out.println("Entity.Order cancelled due to timeout: "+orderId);
               cart.remove(orderId);
           } else {
               order.confirmOrder();
               orderMap.put(orderId, order);
               Product product = inventory.getProduct(order.getProductId());
               if (product != null && product.getQuantity() >= order.getQuantity()) {
                   product.setQuantity(product.getQuantity() - order.getQuantity());
                   System.out.println("Entity.Order confirmed: " + orderId);
               } else {
                   System.out.println("Entity.Order cannot be confirmed: insufficient stock");
               }
               cart.remove(orderId);
           }
       } else {
           System.out.println("Entity.Order not found: "+orderId);
       }

    }
}
