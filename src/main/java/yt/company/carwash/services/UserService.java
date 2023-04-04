package yt.company.carwash.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Permission;
import yt.company.carwash.models.User;
import yt.company.carwash.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
