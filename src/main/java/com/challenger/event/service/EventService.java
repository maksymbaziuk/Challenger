package com.challenger.event.service;

import com.challenger.event.bean.Event;

import java.util.List;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
public interface EventService {

    Event createEvent(Event eventEntity, long challengeId);

    Event findEventById(long id);

    List<Event> findAllEvents();

}
