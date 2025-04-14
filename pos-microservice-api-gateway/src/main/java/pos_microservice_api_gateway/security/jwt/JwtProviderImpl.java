package pos_microservice_api_gateway.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pos_microservice_api_gateway.model.User;
import pos_microservice_api_gateway.security.UserPrincipal;
import pos_microservice_api_gateway.util.SecurityUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements IJwtProvider {

    @Value("${jwt.key.secret}")
    private String SECRET_KEY;

    @Value("${jwt.key.expiration}")
    private int TIME_EXPIRATION_KEY;

    //GENERADOR DE TOKEN PARA LA API LOGIN
    @Override
    public String generateToken(UserPrincipal auth){
        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION_KEY))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    //GENERADOR DE TOKEN - CUANDO SE REGISTRE EL USUARIO - LA API REGISTER
    @Override
    public String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRole())
                .claim("userId", user.getId())
                .setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION_KEY))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    @Override
    public Authentication getAuthentication(HttpServletRequest request){

        //OBTIENE LOS TOKEN
        Claims claims = extractClaims(request);
        if (claims == null) {
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();

        if (username == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    @Override
    public Boolean isTokenValid(HttpServletRequest request){
        Claims claims = extractClaims(request);
        if (claims == null) {
            return null;
        }
        if (claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }

    private Claims extractClaims(HttpServletRequest request){
        String token = SecurityUtils.extractAuthTokenFromRequest(request);
        if (token == null) {
            return null;
        }

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
