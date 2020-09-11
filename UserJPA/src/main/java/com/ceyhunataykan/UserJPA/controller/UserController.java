package com.ceyhunataykan.UserJPA.controller;

import com.ceyhunataykan.UserJPA.entity.User;
import com.ceyhunataykan.UserJPA.exception.UserNotFoundException;
import com.ceyhunataykan.UserJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getListAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/list/{id}")
    public User getListById(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable Integer id) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            user.setId(id);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
    }
}
