package com.shoppingmakiia.UserServiceApplication.Service;
import com.shoppingmakiia.UserServiceApplication.Dto.AuthUserDto;
import com.shoppingmakiia.UserServiceApplication.Dto.NewUserDto;
import com.shoppingmakiia.UserServiceApplication.Dto.RequestDto;
import com.shoppingmakiia.UserServiceApplication.Dto.TokenDto;
import com.shoppingmakiia.UserServiceApplication.Entity.AuthRequest;
import com.shoppingmakiia.UserServiceApplication.Repository.AuthUserRepository;
import com.shoppingmakiia.UserServiceApplication.Security.JwtProvider;
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
        Optional<AuthRequest> user = authUserRepository.findByUsername(dto.getUserName());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        LocalDate localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        AuthRequest authUser = AuthRequest.builder()
                .username(dto.getUserName())
                .password(password)
                .role(dto.getRole())
                .fecha(localDateNow)
                .email(dto.getEmail())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthRequest> user = authUserRepository.findByUsername(dto.getUserName());
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
