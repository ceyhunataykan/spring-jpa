package com.ceyhunataykan.UserJPA.controller;

import com.ceyhunataykan.UserJPA.entity.User;
import com.ceyhunataykan.UserJPA.exception.UserNotFoundException;
import com.ceyhunataykan.UserJPA.service.UserService;

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
    public UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/find-by-id/{id}")
    public Optional<User> getFindById(@PathVariable(value = "id") Integer id){
        Optional<User> user = userService.getFindById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException();
        }
        return user;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable Integer id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getFindById(id);
        if (userOptional.isPresent()) {
            user.setId(id);
            userService.save(user);
        } else {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws UserNotFoundException {
        Optional<User> user = userService.getFindById(id);
        if (user.isPresent()) {
            userService.delete(id);
        } else {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
    }
}
