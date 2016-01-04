package com.challenger.event.service;

import com.challenger.bean.BeanConverter;
import com.challenger.challenge.dao.ChallengeDAO;
import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.event.bean.Event;
import com.challenger.event.dao.EventDAO;
import com.challenger.event.entities.EventEntity;
import com.challenger.event.tools.EventTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
@Component
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private ChallengeDAO challengeDAO;
    @Autowired
    private EventTools eventTools;

    @Override
    public Event createEvent(Event event, long challengeId) {
        EventEntity eventEntity = BeanConverter.toEventEntity(event);
        Long id = eventDAO.createEvent(eventEntity);
        ChallengeEntity challengeEntity = challengeDAO.findChallenge(challengeId);
        if (challengeEntity != null){
            eventTools.addEventToChallenge(eventEntity, challengeEntity);
            challengeDAO.updateChallenge(challengeEntity);
        }
        return findEventById(id);
    }

    @Override
    public Event findEventById(long id) {
        EventEntity eventEntity = eventDAO.findById(id);
        return eventEntity != null ? BeanConverter.toEvent(eventEntity) : null;
    }

    @Override
    public List<Event> findAllEvents() {
        List<Event> events = new ArrayList<>();
        List<EventEntity> eventEntities = eventDAO.findAll();
        if (eventEntities != null){
            for (EventEntity eventEntity : eventEntities){
                events.add(BeanConverter.toEvent(eventEntity));
            }
        }
        return events;
    }
}
