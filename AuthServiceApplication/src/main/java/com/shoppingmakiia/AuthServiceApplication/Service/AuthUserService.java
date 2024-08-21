package com.shoppingmakiia.AuthServiceApplication.Service;
import com.shoppingmakiia.AuthServiceApplication.Dto.AuthUserDto;
import com.shoppingmakiia.AuthServiceApplication.Dto.NewUserDto;
import com.shoppingmakiia.AuthServiceApplication.Dto.RequestDto;
import com.shoppingmakiia.AuthServiceApplication.Dto.TokenDto;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import com.shoppingmakiia.AuthServiceApplication.Repository.AuthUserRepository;
import com.shoppingmakiia.AuthServiceApplication.Security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    public AuthRequest save(NewUserDto dto) {
        Optional<AuthRequest> user = authUserRepository.findByUsername(dto.getUsername());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        LocalDate localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        AuthRequest authUser = AuthRequest.builder()
                .username(dto.getUsername())
                .password(password)
                .role(dto.getRole())
                .email(dto.getEmail())
                .fecha(localDateNow)
                .build();
        return authUserRepository.save(authUser);
    }
    public TokenDto login(AuthUserDto dto) {
        Optional<AuthRequest> user = authUserRepository.findByUsername(dto.getUsername());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }
    public TokenDto validate(String token, RequestDto dto) {
        if(!jwtProvider.validate(token,dto))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUsername(username).isPresent())
            return null;
        return new TokenDto(token);
    }

}
