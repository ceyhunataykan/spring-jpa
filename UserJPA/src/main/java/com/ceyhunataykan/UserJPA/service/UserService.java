package com.ceyhunataykan.UserJPA.service;

import com.ceyhunataykan.UserJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

}
