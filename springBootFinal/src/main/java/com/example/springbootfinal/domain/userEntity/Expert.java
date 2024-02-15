package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.other.Wallet;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
public class Expert extends BaseUser  {

    @Lob
    //@Type(type = "org.hibernate.type.ImageType")
    byte[] personalPhoto;

    @Max(5)
    Integer stars;

    @Enumerated(value = EnumType.STRING)
    ExpertStatus expertStatus;

    @OneToOne
    Wallet wallet;

    @ManyToOne
    Admin admin;


    @OneToMany(mappedBy = "expert")
    List<Suggestion>suggestionList;

    public Expert(String firstName, String lastName, String email, String userName, String password,
                  LocalDate dateOfSigningIn, byte[] personalPhoto, Integer stars, ExpertStatus expertStatus,Wallet wallet) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);
        this.personalPhoto = personalPhoto;
        this.stars = stars;
        this.expertStatus = expertStatus;
        this.wallet=wallet;
    }
    public Expert(String firstName, String lastName, String email, String userName, String password,
                  LocalDate dateOfSigningIn,ExpertStatus expertStatus, byte[] personalPhoto) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);
        this.expertStatus=expertStatus;
        this.personalPhoto=personalPhoto;



    }

}
