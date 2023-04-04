package yt.company.carwash.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.User;
import yt.company.carwash.services.UserService;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
    @GetMapping(value ="/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
