package com.challenger.challenge.service;

import com.challenger.bean.BeanConverter;
import com.challenger.challenge.bean.Challenge;
import com.challenger.challenge.bean.UpdateChallengeRequest;
import com.challenger.challenge.dao.ChallengeDAO;
import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.challenge.tools.ChallengeTools;
import com.challenger.tools.DateTools;
import com.challenger.user.bean.User;
import com.challenger.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
@Component
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeDAO challengeDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private ChallengeTools challengeTools;
    @Autowired
    private DateTools dateTools;

    @Override
    public Object validateChallengeRequest(UpdateChallengeRequest challengeRequest) {
        Object result = null;
        String startDate = challengeRequest.getStartDate();
        if (!dateTools.validateDate(startDate)){
            result = "Start date is incorrect!";
        }
        String deadline = challengeRequest.getDeadline();
        if (!dateTools.validateDate(deadline)){
            result = "Deadline date is incorrect!";
        }
        if (StringUtils.isBlank(challengeRequest.getShortDescription())){
            result = "Short description not set!";
        }
        return result;
    }

    @Override
    public Challenge createChallenge(UpdateChallengeRequest challengeRequest) throws ParseException {
        Challenge challenge = new Challenge();
        challengeTools.applyChallengeCreate(challengeRequest, challenge);
        return createChallenge(challenge);
    }

    public Challenge createChallenge(Challenge challenge){
        Long id = challengeDAO.createChallenge(BeanConverter.toChallengeEntity(challenge, null));
        ChallengeEntity challengeEntity = challengeDAO.findChallenge(id);
        return challengeEntity != null ? BeanConverter.toChallenge(challengeEntity) : null;
    }

    @Override
    public void removeChallenge(long challengeId, User user) {
        if (user != null){
            ChallengeEntity challengeToRemove = null;
            Iterator<Challenge> iter = user.getActualChallenges().iterator();
            while (iter.hasNext()){
                Challenge next = iter.next();
                if (next.getId() == challengeId){
                    challengeToRemove = BeanConverter.toChallengeEntity(next, null);
                    iter.remove();
                    break;
                }
            }
            userService.updateUser(user);
            challengeDAO.removeChallenge(challengeToRemove);
        } else {
          challengeDAO.removeChallenge(challengeId);
        }
    }

    @Override
    public List<Challenge> findAll() {
        List<ChallengeEntity> challengeEntities = challengeDAO.findAll();
        if (challengeEntities == null || challengeEntities.isEmpty()){
            return Collections.emptyList();
        } else {
            List<Challenge> challenges = new ArrayList<>();
            for (ChallengeEntity challengeEntity : challengeEntities){
                challenges.add(BeanConverter.toChallenge(challengeEntity));
            }
            return challenges;
        }
    }

    @Override
    public void updateChallenge(UpdateChallengeRequest updateChallengeRequest) throws ParseException {
        ChallengeEntity challengeEntity = challengeDAO.findChallenge(updateChallengeRequest.getId());
        if (challengeEntity != null){
            Challenge challenge = BeanConverter.toChallenge(challengeEntity);
            challengeTools.applyChallengeUpdate(updateChallengeRequest, challenge);
            challengeDAO.updateChallenge(BeanConverter.toChallengeEntity(challenge, challengeEntity));
        }
    }

    @Override
    public Challenge findById(long id) {
        ChallengeEntity challengeEntity = challengeDAO.findChallenge(id);
        return challengeEntity != null ? BeanConverter.toChallenge(challengeEntity) : null;
    }

    @Override
    public void updateChallenge(Challenge challenge) {
        challengeDAO.updateChallenge(BeanConverter.toChallengeEntity(challenge, null));
    }
}
