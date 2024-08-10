package com.shoppingmakiia.UserServiceApplication.Controller;
import com.shoppingmakiia.UserServiceApplication.Entity.User;
import com.shoppingmakiia.UserServiceApplication.Repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserRepository repository;

     UserController (UserRepository repository) {
         this.repository = repository;
    }

    @GetMapping("/usuarios")
    public List<User> getUsuarios() {
        return repository.findAll();
    }

    @GetMapping("/usuario")
    public User getUsuario(@RequestParam Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
