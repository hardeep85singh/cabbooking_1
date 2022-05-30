package com.example.cabbooking.service;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.user.User;
import com.example.cabbooking.model.user.UserLocation;
import com.example.cabbooking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Hardeep Singh
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    };

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String username, User user) {

        Iterable<User> users = userRepository.findAll();
        User updatedUser = new User();
        for(User findUser : users){
            if(findUser.getUsername().equals(username)){
                userRepository.save(user);
            }
        }
        return updatedUser;
    }

    @Override
    public UserLocation updateUserLocation(String username, UserLocation location) {
        log.info(String.valueOf(location));
        Iterable<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getUsername().equals(username)){
                user.setUserLocation(location);
                userRepository.save(user);
                log.info(String.valueOf(user));
            }
        }
        return location;
    }
}
