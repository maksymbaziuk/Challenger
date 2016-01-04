package com.challenger.user.service;

import com.challenger.bean.BeanConverter;
import com.challenger.user.bean.User;
import com.challenger.user.dao.UserDAO;
import com.challenger.user.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 21.11.2015.
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void login(User user, HttpSession session) {
        session.setAttribute("user", user);
    }

    public User createUser(User user){
        UserEntity userEntity = BeanConverter.toUserEntity(user);
        userDAO.createUser(userEntity);
        return findUser(user.getEmail());
    }

    public User findUser(String email){
        UserEntity userEntity = userDAO.findUser(email);
        return userEntity != null ? BeanConverter.toUser(userEntity) : null;
    }

    public User findUser(long id){
        UserEntity userEntity = userDAO.findUser(id);
        return userEntity != null ? BeanConverter.toUser(userEntity) : null;
    }

    @Override
    public void removeUser(User user) {
        userDAO.removeUser(BeanConverter.toUserEntity(user));
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> entities = userDAO.findAll();
        if (entities == null || entities.isEmpty()){
            return Collections.emptyList();
        } else {
            List<User> users = new ArrayList<>();
            for (UserEntity entity : entities){
                users.add(BeanConverter.toUser(entity));
            }
            return users;
        }
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(BeanConverter.toUserEntity(user));
    }

    @Override
    public User findUserByChallengeId(long id) {
        UserEntity userEntity = userDAO.findUserByChallengeId(id);
        return userEntity != null ? BeanConverter.toUser(userEntity) : null;
    }
}
