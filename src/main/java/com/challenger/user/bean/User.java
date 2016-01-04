package com.challenger.user.bean;

import com.challenger.challenge.bean.Challenge;
import com.challenger.security.UserAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 16.11.2015.
 */
public class User implements UserDetails{

    private long id;
    private String email;
    private String password;
    private Gender gender;
    private List<Challenge> actualChallenges;
    private LocalDate registrationDate;
    private List<UserAuthority> userAuthorities;

    public void setUserAuthorities(List<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Challenge> getActualChallenges() {
        return actualChallenges;
    }

    public void setActualChallenges(List<Challenge> actualChallenges) {
        this.actualChallenges = actualChallenges;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<UserAuthority> getAuthorities() {
        //TODO handle user authorities, grand all for now
        return userAuthorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        //Not implemented
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //Not implemented
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //Not implemented
        return true;
    }

    @Override
    public boolean isEnabled() {
        //Not implemented
        return true;
    }
}
