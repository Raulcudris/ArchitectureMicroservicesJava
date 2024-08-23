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

    CustmomerFeingClient custmomerFeingClient;
    public CustomerService(){
          this.custmomerFeingClient = new CustmomerFeingClient() {
            @Override
            public Customer saveCustomer(Customer customer) {
                Customer customerNew = custmomerFeingClient.saveCustomer(customer);
                return  customerNew;
            }

            @Override
            public List<Customer> getCustomerId(Long id) {
                List<Customer> customer = custmomerFeingClient.getCustomerId(id);
                return  customer;
            }
        };


    }

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

    /*public List<Customer> getCustomerIdFeingClient (Long customerId){
        List<Customer> customers = restTemplate.restTemplate().getForObject("http://Customer-Services/customer/"+customerId,List.class);
        return  customers;
    }*/


}
