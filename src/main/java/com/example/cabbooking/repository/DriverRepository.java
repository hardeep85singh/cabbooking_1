package com.example.cabbooking.repository;

import com.example.cabbooking.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hardeep Singh
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {
    @Query(value = "select * from driver where name = :name",nativeQuery = true)
    Driver findByDriverName(String name);
}
