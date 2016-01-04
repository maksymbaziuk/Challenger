package com.challenger.challenge.bean;

import com.challenger.event.bean.Event;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
public class Challenge {

    private long id;
    private String shortDescription;
    private String longDescription;
    private LocalDate setDate;
    private LocalDate deadline;
    private ChallengeStatus challengeStatus;
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public ChallengeStatus getChallengeStatus() {
        return challengeStatus;
    }

    public void setChallengeStatus(ChallengeStatus challengeStatus) {
        this.challengeStatus = challengeStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDate getSetDate() {
        return setDate;
    }

    public void setSetDate(LocalDate setDate) {
        this.setDate = setDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
