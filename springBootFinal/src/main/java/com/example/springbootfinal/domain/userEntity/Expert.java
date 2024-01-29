package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.other.Wallet;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Expert extends BaseUser {

    @Lob
    byte[] personalPhoto;

    Integer stars;

    @Enumerated(value = EnumType.STRING)
    ExpertStatus expertStatus;

    @OneToOne
    Wallet wallet;

    @ManyToOne
    Admin admin;

    public Expert(String firstName, String lastName, String email, String userName, String password,
                  LocalDate dateOfSigningIn, byte[] personalPhoto, Integer stars, ExpertStatus expertStatus) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);
        this.personalPhoto = personalPhoto;
        this.stars = stars;
        this.expertStatus = expertStatus;

    }

    public Expert(String firstName, String lastName, String email, String userName, String password,
                  LocalDate dateOfSigningIn,ExpertStatus expertStatus, byte[] personalPhoto) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);
        this.expertStatus=expertStatus;
        this.personalPhoto=personalPhoto;



    }

}
