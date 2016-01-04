package com.challenger.event.dao;

import com.challenger.event.entities.EventEntity;

import java.util.List;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
public interface EventDAO {

    Long createEvent(EventEntity eventEntity);

    EventEntity findById(long id);

    List<EventEntity> findAll();
}
