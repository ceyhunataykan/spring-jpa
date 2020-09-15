package com.ceyhunataykan.UserJPA.service;

import com.ceyhunataykan.UserJPA.entity.Employee;
import com.ceyhunataykan.UserJPA.entity.User;
import com.ceyhunataykan.UserJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getFindById(Integer id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

}
