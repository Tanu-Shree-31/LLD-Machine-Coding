package service;

import exception.EcommerceException;
import model.Product;

public interface ProductService {

    String addProduct(Product product) throws EcommerceException;
    Product getProduct(String productId) throws EcommerceException;
    boolean checkInventory(int orderedQuantity, String productId) throws EcommerceException;
}
