package service;

import exception.EcommerceException;
import model.Order;

public interface OrderService {
    String addOrder(Order order) throws EcommerceException;
    Order getOrder(String orderId) throws EcommerceException;
}
