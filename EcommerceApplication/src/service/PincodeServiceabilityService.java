package service;

import exception.EcommerceException;
import model.PaymentMode;
public interface PincodeServiceabilityService {

    Boolean createPinCodeServiceability(String sourcePinCode,
                                       String destinationPinCode,
                                       PaymentMode paymentMode) throws EcommerceException;
    Boolean checkIsSourceAndDestPinCodeMatchesForPaymentType(
            String sourcePinCode,
            String destinationPinCode,
            PaymentMode paymentMode
    ) throws EcommerceException;

}
