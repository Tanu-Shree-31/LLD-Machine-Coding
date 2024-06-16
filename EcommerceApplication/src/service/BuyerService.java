package service;

import exception.EcommerceException;
import model.Buyer;


public interface BuyerService {
    String addBuyer(Buyer buyer) throws EcommerceException;
    Buyer getBuyer(String buyerId) throws EcommerceException;
}
