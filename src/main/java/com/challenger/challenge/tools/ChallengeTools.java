package com.challenger.challenge.tools;

import com.challenger.challenge.bean.Challenge;
import com.challenger.challenge.bean.ChallengeStatus;
import com.challenger.challenge.bean.UpdateChallengeRequest;
import com.challenger.tools.DateTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Maksym_Baziuk on 21.11.2015.
 */
@Component
public class ChallengeTools {

    @Autowired
    private DateTools dateTools;

    public void applyChallengeUpdate(UpdateChallengeRequest challengeRequest, Challenge challenge) throws ParseException{
        challenge.setShortDescription(challengeRequest.getShortDescription());
        challenge.setLongDescription(challengeRequest.getLongDescription());
        challenge.setDeadline(LocalDate.parse(challengeRequest.getDeadline()));
        if (StringUtils.isNotBlank(challengeRequest.getStartDate())){
            challenge.setSetDate(LocalDate.parse(challengeRequest.getStartDate()));
        } else {
            challenge.setSetDate(LocalDate.now());
        }
        if (challengeRequest.getChallengeStatus() > 0){
            challenge.setChallengeStatus(ChallengeStatus.fromCode(challengeRequest.getChallengeStatus()));
        }
    }

    public void applyChallengeCreate(UpdateChallengeRequest challengeRequest, Challenge challenge) throws ParseException {
        applyChallengeUpdate(challengeRequest, challenge);
        challenge.setChallengeStatus(ChallengeStatus.CREATED);
    }

}
