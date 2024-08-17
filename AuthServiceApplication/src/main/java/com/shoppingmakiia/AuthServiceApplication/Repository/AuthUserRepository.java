package com.shoppingmakiia.AuthServiceApplication.Repository;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthUserRepository extends JpaRepository<AuthRequest, Integer> {

    String FILTER_USERNAME_QUERY = "SELECT c FROM EntyRecmaeagendahma c  WHERE c.recSecregReat  = :username";
    @Query(value = FILTER_USERNAME_QUERY)
    Optional<AuthRequest> findByUsername(@Param("username") String username);
}
