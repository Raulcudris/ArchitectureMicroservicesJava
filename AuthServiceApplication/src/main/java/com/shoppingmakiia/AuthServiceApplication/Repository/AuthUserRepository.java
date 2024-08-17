package com.shoppingmakiia.AuthServiceApplication.Repository;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthUserRepository extends JpaRepository<AuthRequest, Integer> {

    Optional<AuthRequest> findByUsername(String username);
}
