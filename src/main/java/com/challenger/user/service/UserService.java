package com.challenger.user.service;

import com.challenger.user.bean.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 21.11.2015.
 */
public interface UserService {

    void login(User user, HttpSession session);

    User createUser(User user);

    User findUser(String email);

    User findUser(long id);

    void removeUser(User user);

    List<User> findAll();

    void updateUser(User user);

    User findUserByChallengeId(long id);

}
