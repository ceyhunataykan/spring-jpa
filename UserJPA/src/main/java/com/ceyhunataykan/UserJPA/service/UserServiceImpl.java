package com.ceyhunataykan.UserJPA.service;

import com.ceyhunataykan.UserJPA.entity.User;
import com.ceyhunataykan.UserJPA.exception.GenericNotFoundException;
import com.ceyhunataykan.UserJPA.helper.ExcelDownload;
import com.ceyhunataykan.UserJPA.helper.ExcelUpload;
import com.ceyhunataykan.UserJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User userBody) {
        return userRepository.save(userBody);
    }

    @Override
    public void update(User user, Integer id) {
        User optionalUser = getFindById(id);
        if (!(optionalUser == null)) {
            user.setId(id);
            userRepository.save(user);
        } else {
            throw new GenericNotFoundException();
        }
    }

    @Override
    public void delete(Integer id) {
        User optionalUser = getFindById(id);
        if (optionalUser != null) {
            userRepository.deleteById(id);
        } else {
            throw new GenericNotFoundException();
        }
    }

    @Override
    public User getFindById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new GenericNotFoundException();
        }
        return optionalUser.get();
    }

    @Override
    public ByteArrayInputStream excelOutput() {
        List<User> users = userRepository.findAll();

        ByteArrayInputStream in = ExcelDownload.usersToExcel(users);
        return in;
    }

    @Override
    public void excelInput(MultipartFile file) {
        try {
            List<User> tutorials = ExcelUpload.excelToTutorials(file.getInputStream());
            userRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
