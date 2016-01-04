package com.challenger.challenge.controller;

import com.challenger.challenge.bean.Challenge;
import com.challenger.challenge.bean.UpdateChallengeRequest;
import com.challenger.challenge.service.ChallengeService;
import com.challenger.user.bean.User;
import com.challenger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @ResponseBody
    public Object create(@ModelAttribute UpdateChallengeRequest challengeRequest, HttpSession session) throws ParseException {
        Object result = challengeService.validateChallengeRequest(challengeRequest);
        if (result == null){
            long userId = challengeRequest.getUserId();
            //Setting id of current user if it's empty in the request
            if (userId == 0){
                User user = (User) session.getAttribute("user");
                //Don't need to check if null, we have filter that won't pass request
                // here if there is no user in session
                userId = user.getId();
            }
            User user = userService.findUser(challengeRequest.getUserId());
            if (user != null){
                //We can throw ParseException because it can't happen. Date was validated before
                Challenge challenge = challengeService.createChallenge(challengeRequest);
                if (user.getActualChallenges() == null){
                    user.setActualChallenges(new ArrayList<>());
                }
                user.getActualChallenges().add(challenge);
                userService.updateUser(user);
                result = challenge;
            } else {
                result = "User doesn't exist";
            }
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@ModelAttribute UpdateChallengeRequest updateChallengeRequest) throws ParseException {
        challengeService.updateChallenge(updateChallengeRequest);
        Challenge challenge = challengeService.findById(updateChallengeRequest.getId());
        return challenge;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Object remove(@RequestParam  long id){
        Object result = null;
        if (id != 0){
            User user = userService.findUserByChallengeId(id);
            challengeService.removeChallenge(id, user);
            result = user;
        } else {
            result = "Challenge Id not specified!";
        }
        return result;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Object allChallenges(){
        return challengeService.findAll();
    }

}
