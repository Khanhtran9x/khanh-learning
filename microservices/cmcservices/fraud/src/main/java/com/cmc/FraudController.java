package com.cmc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fraud-check")
@Slf4j
public class FraudController {
    @Autowired
    IFraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public ResponseEntity<?> isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer  = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new ResponseEntity<>(isFraudulentCustomer, HttpStatus.OK);
    }
}
