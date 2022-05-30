package com.example.cabbooking.service;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.user.User;
import com.example.cabbooking.model.user.UserLocation;

/**
 * @author Hardeep Singh
 */
public interface UserService {
    User addUser(User user);

    User updateUser(String username, User user);

    UserLocation updateUserLocation(String username, UserLocation location);

}
