package com.challenger.challenge.bean;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
public class UpdateChallengeRequest {

    private String shortDescription;
    private String longDescription;
    private String startDate;
    private String deadline;
    private long id;
    private long userId;
    private int challengeStatus;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getChallengeStatus() {
        return challengeStatus;
    }

    public void setChallengeStatus(int challengeStatus) {
        this.challengeStatus = challengeStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long userid) {
        this.id = userid;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
