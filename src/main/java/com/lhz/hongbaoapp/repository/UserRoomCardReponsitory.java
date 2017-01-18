package com.lhz.hongbaoapp.repository;

import com.lhz.hongbaoapp.entity.UserRoomCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LHZ on 2017/1/17.
 */
public interface UserRoomCardReponsitory{
    @Modifying
    @Query("DELETE UserRoomCard WHERE username = :username and id= :id")
    Integer deleteRoomCard(@Param("username") String username, @Param("id") int id);
    List<UserRoomCard> findByUserName(String username);

}
