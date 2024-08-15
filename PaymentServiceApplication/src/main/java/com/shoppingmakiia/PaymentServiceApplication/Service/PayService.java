package com.shoppingmakiia.PaymentServiceApplication.Service;
import com.shoppingmakiia.PaymentServiceApplication.Entity.Pay;
import com.shoppingmakiia.PaymentServiceApplication.Repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayService {
    @Autowired
    PayRepository payRepository;

    public List<Pay> getAll(){
        return  payRepository.findAll();
    }
    public Pay getPayById( Long id){
        return payRepository.findById(id).orElse(null);
    }
    public Pay save(Pay pay){
        Pay productNew = payRepository.save(pay);
        return productNew;
    }
}
