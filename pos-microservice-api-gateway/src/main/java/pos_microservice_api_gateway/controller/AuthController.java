package pos_microservice_api_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos_microservice_api_gateway.model.User;
import pos_microservice_api_gateway.service.interfaces.IAuthService;
import pos_microservice_api_gateway.service.interfaces.IUserService;

import static pos_microservice_api_gateway.util.Constants.*;

@RestController
@RequestMapping(AUTH)
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    @PostMapping(SIGNUP)
    public ResponseEntity<?> signUp(@RequestBody User user){
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> signIn(@RequestBody User user){
        return ResponseEntity.ok(authService.signIn(user));
    }


}
