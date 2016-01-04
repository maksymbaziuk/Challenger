package com.challenger.user.controller;

import com.challenger.security.UserAuthority;
import com.challenger.user.bean.Gender;
import com.challenger.user.bean.RegistrationRequest;
import com.challenger.user.bean.User;
import com.challenger.user.service.UserService;
import com.challenger.user.validation.UserValidationManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.challenger.user.bean.UserResponseMessage.*;

/**
 * Created by Maksym_Baziuk on 16.11.2015.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidationManager userValidationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session){
        if (!EmailValidator.getInstance().isValid(email)){
            return INVALID_EMAIL_ADDRESS;
        }
        User user = userService.findUser(email);
        if (user == null){
            return NOT_REGISTERED;
        } else {
            session.setAttribute("user", user);
            return user;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    @ResponseBody
    public Object registration (@ModelAttribute  RegistrationRequest registrationRequest, HttpServletResponse httpServletResponse){
        if (!userValidationManager.validateRegistrationRequest(registrationRequest)){
            //TODO change HTTP code
            httpServletResponse.setStatus(401);
        }
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setGender(Gender.fromCode(registrationRequest.getGender()));
        user.setRegistrationDate(LocalDate.now());
        List<UserAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(UserAuthority.USER);
        user.setUserAuthorities(userAuthorities);
        user = userService.createUser(user);
        return user;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Object findUser(@RequestParam(required = false) String email, @RequestParam(required = false) String id){
        Object result = null;
        if (StringUtils.isNotBlank(id) && NumberUtils.isDigits(id)){
            User user = userService.findUser(Long.parseLong(id));
            result = user != null ? user : NOT_REGISTERED;
        } else if (StringUtils.isNotBlank(email)) {
            User user = userService.findUser(email);
            result = user != null ? user : NOT_REGISTERED;
        } else {
            result = EMAIL_OR_ID_NOT_SET;
        }
        return result;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Object removeUser(@RequestParam(required = false) String email, @RequestParam(required = false) String id){
        Object result = null;
        User user = null;
        if (StringUtils.isNotBlank(id) && NumberUtils.isDigits(id)){
            user = userService.findUser(Long.parseLong(id));
        } else if (StringUtils.isNotBlank(email)) {
            user = userService.findUser(email);
        } else {
            result = EMAIL_OR_ID_NOT_SET;
        }
        if (user != null){
            userService.removeUser(user);
            result = USER_REMOVED;
        } else if (result == null){
            result = NOT_REGISTERED;
        }
        return result;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Object allUsers(){
        return userService.findAll();
    }
}
