package com.challenger.user.dao;

import com.challenger.user.bean.User;
import com.challenger.user.entities.UserEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
@Transactional
public interface UserDAO {

    Long createUser(UserEntity user);

    UserEntity findUser(String email);

    UserEntity findUser(long id);

    void removeUser(UserEntity user);

    List<UserEntity> findAll();

    void updateUser(UserEntity user);

    UserEntity findUserByChallengeId(long id);

}
