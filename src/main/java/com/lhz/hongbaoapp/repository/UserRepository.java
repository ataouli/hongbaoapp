package com.lhz.hongbaoapp.repository;

import com.lhz.hongbaoapp.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LHZ on 2017/1/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    @Modifying
    @Query("UPDATE User SET password = :password WHERE username = :username")
    Integer updatePassword(@Param("username") String username, @Param("password") String password);
    @Modifying
    @Query("UPDATE User SET phone = :phone WHERE username = :username")
    Integer updatePhone(@Param("username") String username, @Param("phone") String phone);
    User findByUsername(String username);

}
