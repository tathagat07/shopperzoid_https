package com.stackroute.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stackroute.service.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/payment")
@CrossOrigin("*")
public class Controller {

    private StripeClient stripeClient;

    @Autowired
    public Controller(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(HttpServletRequest request) throws StripeException {
        //String token =  request.getHeader("token");



        Double amount = Double.parseDouble(request.getHeader("amount"));
        String customer = request.getHeader("customer");


        return this.stripeClient.chargeCreditCard(customer,amount);
    }
}
