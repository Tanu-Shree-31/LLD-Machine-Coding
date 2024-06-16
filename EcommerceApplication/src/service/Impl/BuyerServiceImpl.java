package service.Impl;

import exception.EcommerceException;
import model.Buyer;
import repository.BuyerRepository;

import service.BuyerService;

public class BuyerServiceImpl implements BuyerService {

    BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @Override
    public String addBuyer(Buyer buyer) throws EcommerceException {
        Buyer createdBuyer = buyerRepository.createBuyer(buyer);
        return createdBuyer.getBuyerId();
    }

    @Override
    public Buyer getBuyer(String buyerId) throws EcommerceException {
        return buyerRepository.getBuyer(buyerId);
    }
}
