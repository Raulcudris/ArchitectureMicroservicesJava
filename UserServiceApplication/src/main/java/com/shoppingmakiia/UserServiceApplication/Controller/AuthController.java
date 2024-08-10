package com.shoppingmakiia.UserServiceApplication.Controller;
import com.shoppingmakiia.UserServiceApplication.Dto.AuthUserDto;
import com.shoppingmakiia.UserServiceApplication.Dto.NewUserDto;
import com.shoppingmakiia.UserServiceApplication.Dto.TokenDto;
import com.shoppingmakiia.UserServiceApplication.Entity.AuthRequest;
import com.shoppingmakiia.UserServiceApplication.Service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthUserService authUserService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto){
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRequest> create(@RequestBody NewUserDto dto){
        AuthRequest authUser = authUserService.save(dto);
        if(authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }

}
