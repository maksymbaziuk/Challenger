package com.challenger.user.dao;

import com.challenger.dao.GenericDaoImpl;
import com.challenger.user.entities.UserEntity;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
@Repository
public class UserDAOImpl extends GenericDaoImpl<UserEntity, Long> implements UserDAO{

    public UserDAOImpl(){
        super(UserEntity.class);
    }

    @Override
    public Long createUser(UserEntity user) {
        return create(user);
    }

    @Override
    public UserEntity findUser(String email) {
        return (UserEntity) getSession().createCriteria(getType()).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public UserEntity findUser(long id) {
        return read(id);
    }

    @Override
    public void removeUser(UserEntity user) {
        delete(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return getSession().createCriteria(getType()).list();
    }

    @Override
    public void updateUser(UserEntity user) {
        update(user);
    }

    @Override
    public UserEntity findUserByChallengeId(long id) {
        Query query = getSession().createQuery("from " + getType().getSimpleName() + " where :id MEMBER OF actualChallenges");
        query.setParameter("id", id);
        return (UserEntity) query.uniqueResult();
    }
}
