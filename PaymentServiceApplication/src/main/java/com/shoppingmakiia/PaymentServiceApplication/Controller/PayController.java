package com.shoppingmakiia.PaymentServiceApplication.Controller;
import com.shoppingmakiia.PaymentServiceApplication.Entity.Pay;
import com.shoppingmakiia.PaymentServiceApplication.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    PayService payService;

    @GetMapping("getall")
    public ResponseEntity<List<Pay>> getAll(){
        List<Pay> products = payService.getAll();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pay> getById(@PathVariable("id") Long id){
        Pay pay = payService.getPayById(id);
        if(pay == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pay);
    }

    @PostMapping("create")
    public ResponseEntity<Pay> save(@RequestBody Pay pay){
        Pay payNew = payService.save(pay);
        return ResponseEntity.ok(payNew);
    }


}
