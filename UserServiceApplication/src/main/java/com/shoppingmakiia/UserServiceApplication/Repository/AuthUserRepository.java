package com.shoppingmakiia.UserServiceApplication.Repository;
import com.shoppingmakiia.UserServiceApplication.Entity.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthRequest, Long> {
    Optional<AuthRequest> findByUsername(String username);
}
