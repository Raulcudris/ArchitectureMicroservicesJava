package com.shoppingmakiia.CustomerServicesApplication.feignclients;

import com.shoppingmakiia.CustomerServicesApplication.Dto.CustomerDto;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "Customer-Services", url = "http://Customer-Services")
public interface CustmomerFeingClient {
    @PostMapping("/customer/create")
    Customer saveCustomer(@RequestBody Customer customer);
    @GetMapping("/customer/{userId}")
    List<Customer> getCustomerId(@PathVariable("id") Long id);
}
