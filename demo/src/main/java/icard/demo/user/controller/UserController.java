package icard.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import icard.demo.user.model.UserEntity;
import icard.demo.user.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable("id") int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable("id") int id, @RequestBody UserEntity updatedUser) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
