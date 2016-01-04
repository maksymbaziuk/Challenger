package com.challenger.bean;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
public class ResponseMessage {

    protected ResponseMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

}
