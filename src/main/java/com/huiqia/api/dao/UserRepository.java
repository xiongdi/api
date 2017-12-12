package com.huiqia.api.dao;

import com.huiqia.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findUsersByArea(String area);

    @Query("select u from User u where u.name=:name")
    User findUserByName(String name);
}
