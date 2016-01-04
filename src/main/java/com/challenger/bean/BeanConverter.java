package com.challenger.bean;

import com.challenger.challenge.bean.Challenge;
import com.challenger.challenge.bean.ChallengeStatus;
import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.event.bean.Event;
import com.challenger.event.entities.EventEntity;
import com.challenger.security.UserAuthority;
import com.challenger.user.bean.Gender;
import com.challenger.user.bean.User;
import com.challenger.user.entities.UserEntity;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
public final class BeanConverter {

    private BeanConverter(){
        throw new UnsupportedOperationException();
    }

    public static User toUser(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setGender(Gender.fromCode(userEntity.getGender()));
        user.setRegistrationDate(userEntity.getRegistrationDate());
        user.setUserAuthorities(new ArrayList<>(userEntity.getUserAuthorities()));
        if (userEntity.getActualChallenges() != null){
            List<Challenge> challenges = new ArrayList<>();
            for (ChallengeEntity entity : userEntity.getActualChallenges()){
                challenges.add(toChallenge(entity));
            }
            user.setActualChallenges(challenges);
        }
        return user;
    }

    public static UserEntity toUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        if (user.getId() != 0){
            userEntity.setId(user.getId());
        }
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setGender(user.getGender().getCode());
        userEntity.setRegistrationDate(user.getRegistrationDate());
        userEntity.setUserAuthorities(new ArrayList<>(user.getAuthorities()));
        if (user.getActualChallenges() != null){
            List<ChallengeEntity> challenges = new ArrayList<>();
            for (Challenge challenge : user.getActualChallenges()){
                challenges.add(toChallengeEntity(challenge, null));
            }
            userEntity.setActualChallenges(challenges);
        }
        return userEntity;
    }

    public static ChallengeEntity toChallengeEntity(Challenge challenge, ChallengeEntity baseEntity){
        ChallengeEntity challengeEntity;
        if (baseEntity != null){
            challengeEntity = baseEntity;
        } else {
            challengeEntity = new ChallengeEntity();
        }
        if (challenge.getId() != 0){
            challengeEntity.setId(challenge.getId());
        }
        if (challenge.getEvents() != null){
            List<EventEntity> eventEntities = new ArrayList<>();
            for (Event event : challenge.getEvents()){
                eventEntities.add(toEventEntity(event));
            }
            challengeEntity.setEventEntities(eventEntities);
        }
        challengeEntity.setShortDescription(challenge.getShortDescription());
        challengeEntity.setLongDescription(challenge.getLongDescription());
        challengeEntity.setSetDate(challenge.getSetDate());
        challengeEntity.setDeadline(challenge.getDeadline());
        challengeEntity.setChallengeStatus(challenge.getChallengeStatus().getCode());
        return challengeEntity;
    }

    public static Challenge toChallenge(ChallengeEntity challengeEntity){
        Challenge challenge = new Challenge();
        challenge.setId(challengeEntity.getId());
        challenge.setShortDescription(challengeEntity.getShortDescription());
        challenge.setLongDescription(challengeEntity.getLongDescription());
        challenge.setSetDate(challengeEntity.getSetDate());
        challenge.setDeadline(challengeEntity.getDeadline());
        challenge.setChallengeStatus(ChallengeStatus.fromCode(challengeEntity.getChallengeStatus()));
        if (challengeEntity.getEventEntities() != null){
            List<Event> events = new ArrayList<>();
            for (EventEntity eventEntity : challengeEntity.getEventEntities()){
                events.add(toEvent(eventEntity));
            }
            challenge.setEvents(events);
        }
        return challenge;
    }

    public static Event toEvent(EventEntity eventEntity){
        Event event = new Event();
        event.setId(eventEntity.getId());
        event.setEventDate(eventEntity.getEventDate());
        return event;
    }

    public static EventEntity toEventEntity(Event event){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(event.getId());
        eventEntity.setEventDate(event.getEventDate());
        return eventEntity;
    }

}
