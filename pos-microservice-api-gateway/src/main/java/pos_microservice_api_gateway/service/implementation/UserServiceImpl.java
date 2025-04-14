package pos_microservice_api_gateway.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pos_microservice_api_gateway.model.Role;
import pos_microservice_api_gateway.model.User;
import pos_microservice_api_gateway.repository.IUserRepository;
import pos_microservice_api_gateway.security.jwt.IJwtProvider;
import pos_microservice_api_gateway.service.interfaces.IUserService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IJwtProvider jwtProvider;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        User userCreate = userRepository.save(user);

        String jwt = jwtProvider.generateToken(userCreate);
        userCreate.setToke(jwt);

        return userCreate;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void changeRole(Role role, String username) {
        userRepository.updateUserRole(username, role);
    }

    @Override
    public User getUserByToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("El usuario no existe: " + username));
        String jwt = jwtProvider.generateToken(user);
        user.setToke(jwt);
        return user;
    }

    @Override
    public User  updateUserById(Long id, User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
