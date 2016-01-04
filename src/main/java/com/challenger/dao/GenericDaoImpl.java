package com.challenger.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public abstract class GenericDaoImpl<T, P extends Serializable> implements GenericDao<T, P> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> type;

    public GenericDaoImpl() {}

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public P create(T o) {
        return (P) getSession().save(o);
    }

    @Override
    public T read(P id) {
        return (T) getSession().get(type, id);
    }

    @Override
    public void update(T o) {
        getSession().update(o);
    }

    @Override
    public void delete(T o) {
        getSession().delete(o);
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getType() {
        return type;
    }

}
