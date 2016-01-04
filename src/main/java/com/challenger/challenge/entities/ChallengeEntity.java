package com.challenger.challenge.entities;

import com.challenger.event.entities.EventEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
@Entity
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String shortDescription;
    @Column
    private String longDescription;
    @Column
    private LocalDate setDate;
    @Column
    private LocalDate deadline;
    @Column
    private int challengeStatus = 1;
    @OneToMany
    private List<EventEntity> eventEntities;

    public List<EventEntity> getEventEntities() {
        return eventEntities;
    }

    public void setEventEntities(List<EventEntity> eventEntities) {
        this.eventEntities = eventEntities;
    }

    public int getChallengeStatus() {
        return challengeStatus;
    }

    public void setChallengeStatus(int challengeStatus) {
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

    @Override
    public String toString() {
        return "ChallengeEntity{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", setDate=" + setDate +
                ", deadline=" + deadline +
                '}';
    }
}
