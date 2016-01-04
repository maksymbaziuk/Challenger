package com.challenger.challenge.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksym_Baziuk on 21.11.2015.
 */
public enum ChallengeStatus {

    CREATED(1), STARTED(2), DONE(3), CANCELLED(4);

    private static final Map<Integer, ChallengeStatus> states = new HashMap<>();

    static {
        for (ChallengeStatus status : values()){
            states.put(status.code, status);
        }
    }

    ChallengeStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ChallengeStatus fromCode(int code){
        return states.get(code);
    }
}
