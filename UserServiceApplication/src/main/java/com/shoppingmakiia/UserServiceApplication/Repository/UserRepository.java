package com.shoppingmakiia.UserServiceApplication.Repository;
import com.shoppingmakiia.UserServiceApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
