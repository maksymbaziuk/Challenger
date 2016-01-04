package com.challenger.dao;

import java.io.Serializable;

public interface GenericDao<T, P extends Serializable> {

    P create(T newInstance);

    T read(P id);

    void update(T transientObject);

    void delete(T persistentObject);
}
