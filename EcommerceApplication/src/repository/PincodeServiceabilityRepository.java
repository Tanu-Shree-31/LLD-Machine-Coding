package repository;

import model.PaymentMode;
import model.PinCodeServiceability;


import java.util.HashMap;

public class PincodeServiceabilityRepository {
    // Source  -> ( dest, paymentMode)
    HashMap<String, HashMap<String, PaymentMode>> pinCodes;

    public PincodeServiceabilityRepository(){
        pinCodes = new HashMap<>();
    }

    public Boolean createPinCodeServiceability(String sourcePinCode, PinCodeServiceability pinCodeServiceability){
        if(pinCodes.get(sourcePinCode)==null){
            HashMap<String, PaymentMode> destinationCode = new HashMap<>();
            destinationCode.put(pinCodeServiceability.getDestinationPinCode(), pinCodeServiceability.getPaymentMode());
            pinCodes.put(sourcePinCode,destinationCode);
        }
        pinCodes.get(sourcePinCode).put(pinCodeServiceability.getDestinationPinCode(), pinCodeServiceability.getPaymentMode());

        return true;
    }

    public HashMap<String, PaymentMode> getAllDestinationPincodes(String sourcePinCode){
        return pinCodes.get(sourcePinCode);
    }

}
