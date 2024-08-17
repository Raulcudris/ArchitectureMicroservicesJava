package com.shoppingmakiia.AuthServiceApplication.Controller;
import com.shoppingmakiia.AuthServiceApplication.Dto.AuthUserDto;
import com.shoppingmakiia.AuthServiceApplication.Dto.NewUserDto;
import com.shoppingmakiia.AuthServiceApplication.Dto.RequestDto;
import com.shoppingmakiia.AuthServiceApplication.Dto.TokenDto;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import com.shoppingmakiia.AuthServiceApplication.Service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto){
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto){
        TokenDto tokenDto = authUserService.validate(token, dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRequest> create(@RequestBody NewUserDto dto){
        AuthRequest authRequest = authUserService.save(dto);
        if(authRequest == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authRequest);
    }


}
