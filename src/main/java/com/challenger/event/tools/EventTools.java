package com.challenger.event.tools;

import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.event.entities.EventEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
@Component
public class EventTools {

    public void addEventToChallenge(EventEntity event, ChallengeEntity challenge){
        List<EventEntity> events = challenge.getEventEntities();
        if (events == null){
            events = new ArrayList<>();
        }
        events.add(event);
        challenge.setEventEntities(events);
    }
}
