package Entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> products;

    public Inventory(){
        products = new HashMap<>();
    }

    //add a product to the inventory
    public void addProduct(Product product){
        products.put(product.getProductId(), product);
    }

    // remove a product from the inventory
    public void removeProduct(String productId){
        products.remove(productId);
    }

    // get total inventory value
    public double getTotalInventoryValue(){
        double total=0.0;
        for(Product product: products.values()){
            total=total+product.getPrice()*product.getQuantity();
        }
        return total;
    }

    // get the product details
    public Product getProduct(String productId){
        return products.get(productId);
    }

    // update product quantity
    public void updateProductQuantity(String productId, int quantity){
        Product product=products.get(productId);
        if(product!=null){
            product.setQuantity(quantity);
        }
    }

    //list all the products
    public Map<String, Product> getAllProducts(){
        return products;
    }

}
