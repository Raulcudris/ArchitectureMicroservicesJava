package com.shoppingmakiia.AuthServiceApplication.Repository;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthRequest, Long> {
    Optional<AuthRequest> findByUsername(String username);
}
