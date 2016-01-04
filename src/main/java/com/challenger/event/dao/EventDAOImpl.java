package com.challenger.event.dao;

import com.challenger.dao.GenericDaoImpl;
import com.challenger.event.entities.EventEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
@Repository
public class EventDAOImpl extends GenericDaoImpl<EventEntity, Long> implements EventDAO {

    public EventDAOImpl(){
        super(EventEntity.class);
    }

    @Override
    public Long createEvent(EventEntity eventEntity) {
        return create(eventEntity);
    }

    @Override
    public EventEntity findById(long id) {
        EventEntity eventEntity = read(id);
        return eventEntity;
    }

    @Override
    public List<EventEntity> findAll() {
        return getSession().createCriteria(getType().getSimpleName()).list();
    }
}
