package com.shoppingmakiia.CustomerServicesApplication.Controllers;
import com.shoppingmakiia.CustomerServicesApplication.Dto.CustomerDto;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import com.shoppingmakiia.CustomerServicesApplication.Service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
     @Autowired
     CustomerService customerService;

    @GetMapping("getall")
    public List<CustomerDto> getAll(){
        return customerService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable("id") Long id){
        Customer product = customerService.getCustomerById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(product));
    }
    @PostMapping("create")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto productDto){
        Customer product = convertToEntity(productDto);
        Customer savedProduct = customerService.save(product);
        return ResponseEntity.ok(convertToDto(savedProduct));
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
    private Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

}
