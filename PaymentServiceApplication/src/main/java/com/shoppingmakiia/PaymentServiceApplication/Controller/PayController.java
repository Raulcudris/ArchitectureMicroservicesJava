package com.shoppingmakiia.PaymentServiceApplication.Controller;


import com.shoppingmakiia.PaymentServiceApplication.Entity.Pay;
import com.shoppingmakiia.PaymentServiceApplication.Repository.PayRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PayController {

    private PayRepository repository;

    PayController(PayRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pagos")
    public List<Pay> getPagos() {
        return repository.findAll();
    }

    @GetMapping("/pago")
    public Pay getPago(@RequestParam Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pay not found"));
    }


}
