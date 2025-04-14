package pos_microservice_api_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pos_microservice_api_gateway.model.Role;
import pos_microservice_api_gateway.model.User;
import pos_microservice_api_gateway.security.UserPrincipal;
import pos_microservice_api_gateway.service.interfaces.IUserService;

import static pos_microservice_api_gateway.util.Constants.*;

@RestController
@RequestMapping(API)
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping(USERNAME_GET_ROLE)
    public ResponseEntity<?> updateRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role){
        userService.changeRole(role, userPrincipal.getUsername());
        return ResponseEntity.ok("Rol cambiado");
    }

    @PostMapping(USER)
    public ResponseEntity<?> changeRole(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping(GET_USER_BY_ID)
    public  ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(GET_USER_BY_TOKEN)
    public ResponseEntity<?> getUserByToken(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(userService.getUserByToken(userPrincipal.getUsername()));
    }


    @GetMapping(GET_USER_BY_USERNAME)
    public ResponseEntity<?> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping(GET_ALL_USERS)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping(GET_USER_BY_ID)
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
