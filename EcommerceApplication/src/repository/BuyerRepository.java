package repository;

import exception.EcommerceException;
import model.Buyer;
import model.ErrorCode;
import utils.ErrorCodeMap;

import java.util.HashMap;

public class BuyerRepository {
    HashMap<String, Buyer> buyers;

    public BuyerRepository(){
        buyers = new HashMap<>();
    }

    public Buyer createBuyer(Buyer buyer){
        if(buyers.get(buyer.getBuyerId())!=null){
            throw new EcommerceException(
                    ErrorCode.BUYER_CREATION_FAILED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.BUYER_CREATION_FAILED)
            );
        }
        buyers.put(buyer.getBuyerId(), buyer);
        return buyer;
    }

    public Buyer getBuyer(String buyerId){
        return buyers.get(buyerId);
    }

}
