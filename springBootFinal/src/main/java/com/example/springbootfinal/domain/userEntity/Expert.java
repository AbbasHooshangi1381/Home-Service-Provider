package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.enumurations.Role;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.other.Wallet;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@JsonIgnoreProperties({"admin", "wallet","suggestionList","personalPhoto"})

public class Expert extends BaseUser  {

    @Lob
    byte[] personalPhoto;

    @Max(5)
    Integer stars;

    @Enumerated(value = EnumType.STRING)
    ExpertStatus expertStatus;

    @OneToOne
    Wallet wallet;

    @ManyToOne
    @JsonIgnore
    Admin admin;

    @ManyToMany
    @JoinTable(name = "expert_subduty",
            joinColumns = @JoinColumn(name = "expert_id"),
            inverseJoinColumns = @JoinColumn(name = "subduty_id"))
    List<SubDuty>subDutyList;

    @OneToMany(mappedBy = "expert")
    List<Suggestion>suggestionList;

    public Expert(String firstName, String lastName, String email, String userName, String password,
                  LocalDate dateOfSigningIn, byte[] personalPhoto, Integer stars, ExpertStatus expertStatus,
                  Wallet wallet,Boolean enabled,Role role) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn,enabled,role);
        this.personalPhoto = personalPhoto;
        this.stars = stars;
        this.expertStatus = expertStatus;
        this.wallet=wallet;
    }
    public Expert(String firstName, String lastName, String email, String userName, String password,
                  LocalDate dateOfSigningIn, ExpertStatus expertStatus, byte[] personalPhoto, Boolean enabled, Role role) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn,enabled,role);
        this.expertStatus=expertStatus;
        this.personalPhoto=personalPhoto;
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

}
