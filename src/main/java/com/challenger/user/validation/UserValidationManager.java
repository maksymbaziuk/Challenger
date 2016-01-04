package com.challenger.user.validation;

import com.challenger.user.bean.RegistrationRequest;
import com.challenger.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Maksym_Baziuk on 27.11.2015.
 */
@Component
public class UserValidationManager {

    @Autowired
    public UserService userService;

    public boolean validateRegistrationRequest(RegistrationRequest registrationRequest){
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        if (!EmailValidator.getInstance().isValid(email)
                || StringUtils.isBlank(password)
                || userService.findUser(email) != null){
            return false;
        }
        return true;
    }

}
