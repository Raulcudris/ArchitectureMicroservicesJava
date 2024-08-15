package com.shoppingmakiia.PaymentServiceApplication.Controller;
import com.shoppingmakiia.PaymentServiceApplication.Dto.PayDto;
import com.shoppingmakiia.PaymentServiceApplication.Entity.Pay;
import com.shoppingmakiia.PaymentServiceApplication.Service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    PayService payService;

    @GetMapping("getall")
    public List<PayDto> getAll(){
        return payService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PayDto> getById(@PathVariable("id") Long id){
        Pay pay = payService.getPayById(id);
        if(pay == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(pay));
    }
    @PostMapping("create")
    public ResponseEntity<PayDto> save(@RequestBody PayDto payDto){
        Pay pay = convertToEntity(payDto);
        Pay savedPay = payService.save(pay);
        return ResponseEntity.ok(convertToDto(savedPay));
    }

    private PayDto convertToDto(Pay pay) {
        PayDto payDto = new PayDto();
        BeanUtils.copyProperties(pay, payDto);
        return payDto;
    }
    private Pay convertToEntity(PayDto payDto) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDto, pay);
        return pay;
    }
}
