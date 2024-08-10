package com.shoppingmakiia.PaymentServiceApplication.Repository;

import com.shoppingmakiia.PaymentServiceApplication.Entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay , Long> {
}
