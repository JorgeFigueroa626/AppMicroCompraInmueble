package pos_microservice_api_gateway.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pos_microservice_api_gateway.model.User;
import pos_microservice_api_gateway.security.UserPrincipal;

public interface IJwtProvider {

    public String generateToken(UserPrincipal auth);

    String generateToken(User user);

    public Authentication getAuthentication(HttpServletRequest request);

    public Boolean isTokenValid(HttpServletRequest request);
}
