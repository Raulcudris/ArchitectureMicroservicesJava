package com.shoppingmakiia.CustomerServicesApplication.Service;
import com.shoppingmakiia.CustomerServicesApplication.Config.RestTemplateConfig;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import com.shoppingmakiia.CustomerServicesApplication.Repository.CustomerRepository;
import com.shoppingmakiia.CustomerServicesApplication.feignclients.CustmomerFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestTemplateConfig restTemplate;
    @Autowired
    CustmomerFeingClient custmomerFeingClient;


    public List<Customer> getAll(){
        return  customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        Customer customerId = customerRepository.findByCustomerId(id);
        return  customerId;
    }

    public Customer save(Customer customer){
        Customer productNew = customerRepository.save(customer);
        return productNew;
    }

    public List<Customer> getCustomerIdFeingClient (Long customerId){
        List<Customer> customers = restTemplate.restTemplate().getForObject("http://Customer-Services/customer/"+customerId,List.class);
        return  customers;
    }

    public Customer saveCustomerFeingClient(Customer customer){
        Customer customerNew = custmomerFeingClient.saveCustomer(customer);
        return  customerNew;
    }

    public List<Customer> getCustomerId (Long userId){
        List<Customer> customer = custmomerFeingClient.getCustomerId(userId);
        return  customer;
    }

}
