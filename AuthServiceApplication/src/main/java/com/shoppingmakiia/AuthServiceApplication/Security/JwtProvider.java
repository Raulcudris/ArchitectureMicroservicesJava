package com.shoppingmakiia.AuthServiceApplication.Security;
import com.shoppingmakiia.AuthServiceApplication.Dto.RequestDto;
import com.shoppingmakiia.AuthServiceApplication.Entity.AuthRequest;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
        // No es necesario codificar la clave secreta en Base64
        // secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(AuthRequest authRequest) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authRequest.getId());
        claims.put("role", authRequest.getRole());

        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000); // 1 hora

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()) // Usa la clave en formato byte[]
                .compact();
    }

    public boolean validate(String token, RequestDto dto) {
        try {
            // Usa parseClaimsJws para tokens firmados
            Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            // Manejo de excepciones específicas
            return false;
        }

        // Verifica si el usuario es administrador y si la ruta requiere permisos de admin
        if (!isAdmin(token) && routeValidator.isAdminPath(dto)) {
            return false;
        }
        return true;
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes()) // Usa la clave en formato byte[]
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            // Manejo de excepciones específicas para JWT
            return "bad token";
        }
    }

    private boolean isAdmin(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes()) // Usa la clave en formato byte[]
                    .parseClaimsJws(token)
                    .getBody();

            String role = (String) claims.get("role");
            return "admin".equals(role);
        } catch (JwtException e) {
            // Manejo de excepciones específicas para JWT
            return false;
        }
    }
}
