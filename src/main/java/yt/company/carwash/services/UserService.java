package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.OrderBase;
import yt.company.carwash.models.Permission;
import yt.company.carwash.models.User;
import yt.company.carwash.repository.PermissionRepository;
import yt.company.carwash.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PermissionRepository permissionRepository;




    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User createUser(String email, String password, String rePassword) {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permissionRepository.findByName("ROLE_USER"));
        User user = userRepository.findByEmail(email);
        if(user == null) {
            User account = new User();
            if(password.equals(rePassword)) {
                account.setEmail(email);
                account.setPassword(password);
                account.setPermissions(permissions);
                return userRepository.save(account);
            } else {
                throw new IllegalArgumentException("Passwords are not the same");
            }
        } else {
            throw new IllegalArgumentException("User with this email is already exists");
        }
    }
}
