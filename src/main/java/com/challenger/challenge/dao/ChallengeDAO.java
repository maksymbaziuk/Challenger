package com.challenger.challenge.dao;

import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.user.entities.UserEntity;

import java.util.List;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
public interface ChallengeDAO {

    Long createChallenge(ChallengeEntity challenge);

    void removeChallenge(long id);

    void removeChallenge(ChallengeEntity challenge);

    ChallengeEntity findChallenge(long id);

    List<ChallengeEntity> findAll();

    void updateChallenge(ChallengeEntity challengeEntity);
}
