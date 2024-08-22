package com.shoppingmakiia.CustomerServicesApplication.feignclients;

import com.shoppingmakiia.CustomerServicesApplication.Dto.CustomerDto;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Customer-Services")
@RequestMapping("/customer")
public interface o {
    @PostMapping("create")
    CustomerDto saveCustomer(@RequestBody CustomerDto customer);
    @GetMapping("{userId}")
    List<Customer> getCustomerId(@PathVariable("id") Long id);
}
