package pos_microservice_api_gateway.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pos_microservice_api_gateway.model.User;
import pos_microservice_api_gateway.security.UserPrincipal;
import pos_microservice_api_gateway.security.jwt.IJwtProvider;
import pos_microservice_api_gateway.service.interfaces.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtProvider jwtProvider;

    @Override
    public User signIn(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User userInUser = userPrincipal.getUser();
        userInUser.setToke(jwt);

        return userInUser;
    }
}
