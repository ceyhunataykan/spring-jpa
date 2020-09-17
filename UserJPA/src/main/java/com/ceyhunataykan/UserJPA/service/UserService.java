package com.ceyhunataykan.UserJPA.service;

import com.ceyhunataykan.UserJPA.entity.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    User save(User userBody);

    void update(User user, Integer id);

    void delete(Integer id);

    User getFindById(Integer id);

    ByteArrayInputStream excelLoad();
}
