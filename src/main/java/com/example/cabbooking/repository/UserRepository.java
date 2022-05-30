package com.example.cabbooking.repository;

import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.user.User;
import com.example.cabbooking.model.user.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * @author Hardeep Singh
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select * from users where username = :name",nativeQuery = true)
    User findByUsername(String name);

//    @Modifying
//    @Query("update User u set u.email = ?1, u.phoneNumber = ?2 where u.username = ?3")
//    User updateUserByUsername(String email, Integer phoneNumber, String username );

//    @Modifying
//    @Query("update User u set u.userLocation =?1 where u.username = ?2")
//    UserLocation updateUserLocation(UserLocation userLocation, String username);
}
