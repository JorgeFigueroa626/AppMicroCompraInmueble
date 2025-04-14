package pos_microservice_api_gateway.service.interfaces;

import pos_microservice_api_gateway.model.Role;
import pos_microservice_api_gateway.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User registerUser(User user);
    Optional<User> findByUsername(String username);
    void changeRole(Role role, String username);
    User getUserByToken(String username);

    User updateUserById(Long id, User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
}
