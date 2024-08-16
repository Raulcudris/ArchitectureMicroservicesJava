package com.shoppingmakiia.CustomerServicesApplication.Repository;
import com.shoppingmakiia.CustomerServicesApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Long > {
    String FILTER_PRODUCT_QUERY = "SELECT c FROM Customer c WHERE  c.id = :nroRegCustomer";
    @Query(value = FILTER_PRODUCT_QUERY)
    Customer findByCustomerId(@Param("nroRegCustomer") Long CustomerId);
}
