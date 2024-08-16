package com.shoppingmakiia.AuthServiceApplication.Security;
import com.shoppingmakiia.AuthServiceApplication.Dto.RequestDto;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.timeExpiration}")
    private String timeExpiration;
    @Autowired
    RouteValidator routeValidator;

    //Generate token of access
    public String createToken(AuthRequest authRequest) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(authRequest.getUsername());
        claims.put("id", authRequest.getId());
        claims.put("role",authRequest.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + timeExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSignatureKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validate(String token, RequestDto dto) {
        try {
            Jwts.parser().setSigningKey(getSignatureKey()).parseClaimsJws(token);
             /* Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(timeExpiration)))
                    .signWith((java.security.Key) getSignatureKey(), SignatureAlgorithm.HS256)
                    .compact();*/
        }catch (Exception e){
            return false;
        }
        if(!isAdmin(token) && routeValidator.isAdminPath(dto))
            return false;
        return true;

    }

    public String getUserNameFromToken(String token){
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e) {
            return "bad token";
        }
    }

    private boolean isAdmin(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().get("role").equals("admin");
    }

    public SecretKey getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
