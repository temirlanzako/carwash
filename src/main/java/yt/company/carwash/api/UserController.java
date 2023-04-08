package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.User;
import yt.company.carwash.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestParam String email,
                                        @RequestParam String password,
                                        @RequestParam String rePassword) {
        return ResponseEntity.ok(userService.createUser(email, password, rePassword));
    }
}
