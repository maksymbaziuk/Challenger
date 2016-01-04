package com.challenger.challenge.service;

import com.challenger.challenge.bean.Challenge;
import com.challenger.challenge.bean.UpdateChallengeRequest;
import com.challenger.user.bean.User;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
public interface ChallengeService {

    Object validateChallengeRequest(UpdateChallengeRequest challengeRequest);

    Challenge createChallenge(UpdateChallengeRequest challengeRequest) throws ParseException;

    void removeChallenge (long challengeId, User user);

    List<Challenge> findAll();

    Challenge findById(long id);

    void updateChallenge (UpdateChallengeRequest updateChallengeRequest) throws ParseException;

    void updateChallenge (Challenge challenge);
}
