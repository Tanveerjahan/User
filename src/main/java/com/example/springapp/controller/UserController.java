package com.example.springapp.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springapp.entity.User;
import com.example.springapp.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("user/{id}")
    public User editUser(@PathVariable long id, @RequestBody User user) {
        // Check if the user with the given ID exists in the database
        User existingUser = this.userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Update the existing user's name and age
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            // Save the updated user details
            return this.userRepository.save(existingUser);
        } else {
            // Handle the case when the user with the given ID doesn't exist
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        this.userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
