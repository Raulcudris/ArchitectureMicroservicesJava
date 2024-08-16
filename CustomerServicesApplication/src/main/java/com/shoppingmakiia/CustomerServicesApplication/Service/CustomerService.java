package com.shoppingmakiia.CustomerServicesApplication.Service;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import com.shoppingmakiia.CustomerServicesApplication.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return  customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        Customer customerId = customerRepository.findByCustomerId(id);
        return  customerId;
    }

    public Customer save(Customer product){
        Customer productNew = customerRepository.save(product);
        return productNew;
    }

}
