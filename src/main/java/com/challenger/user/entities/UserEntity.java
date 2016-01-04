package com.challenger.user.entities;

import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.security.UserAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 2, nullable = false)
    private int gender;
    @Column
    private LocalDate registrationDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChallengeEntity> actualChallenges;
    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserAuthority.class)
    private List<UserAuthority> userAuthorities;

    public List<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<ChallengeEntity> getActualChallenges() {
        return actualChallenges;
    }

    public void setActualChallenges(List<ChallengeEntity> actualChallenges) {
        this.actualChallenges = actualChallenges;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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
}
