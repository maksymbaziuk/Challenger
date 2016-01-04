package com.challenger.user.bean;

import com.challenger.bean.ResponseMessage;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
public class UserResponseMessage extends ResponseMessage {
    //TODO move this to resources
    public static UserResponseMessage INVALID_EMAIL_ADDRESS = new UserResponseMessage("Invalid email address");
    public static UserResponseMessage NOT_REGISTERED = new UserResponseMessage("Not registered");
    public static UserResponseMessage USER_REMOVED = new UserResponseMessage("User removed");
    public static UserResponseMessage EMPTY_PASSWORD = new UserResponseMessage("Empty password");
    public static UserResponseMessage ALREADY_EXIST = new UserResponseMessage("User already exist");
    public static UserResponseMessage EMAIL_OR_ID_NOT_SET = new UserResponseMessage("Email or Id should be set");

    protected UserResponseMessage(String message) {
        super(message);
    }

}
