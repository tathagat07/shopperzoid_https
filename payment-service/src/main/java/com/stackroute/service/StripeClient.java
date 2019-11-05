package com.stackroute.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey ="sk_test_qj7NOEUYZpx3QaaBOifcjHmV00V7sa1ohc";
    }

    public Charge chargeCreditCard(String customer, Double amount) throws StripeException {
        Map<String,Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency","INR");
        chargeParams.put("customer", customer);
        //chargeParams.put("source",token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

}
