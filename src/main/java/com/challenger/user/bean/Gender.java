package com.challenger.user.bean;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
public enum Gender {

    MALE(1), FEMALE(2);

    private Gender(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Gender fromCode(int code){
        return code == 1 ? MALE : FEMALE;
    }
}
