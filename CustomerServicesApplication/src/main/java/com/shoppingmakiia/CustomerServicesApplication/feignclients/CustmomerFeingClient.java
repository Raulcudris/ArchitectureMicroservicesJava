package com.shoppingmakiia.CustomerServicesApplication.feignclients;

import com.shoppingmakiia.CustomerServicesApplication.Dto.CustomerDto;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "Customer-Services")
@RequestMapping("/customer")
public interface CustmomerFeingClient {
    @PostMapping("create")
    Customer saveCustomer(@RequestBody Customer customer);
    @GetMapping("{userId}")
    List<Customer> getCustomerId(@PathVariable("id") Long id);
}
