package domain.userEntity;

import base.domin.BaseEntity;
import domain.enumurations.ExpertStatus;
import domain.other.Comments;
import domain.other.ExpertSubService;
import domain.other.Wallet;
import domain.serviceEntity.Service;
import domain.serviceEntity.SubService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Expert extends BaseEntity<Integer> {

    String firstName;
    String lastName;
    String email;
    String userName;
    String password;
    LocalDate dateOfSigningIn;
    @Lob
    byte[] personalPhoto;
    Integer stars;
    @OneToMany(mappedBy = "expert")
    List<ExpertSubService> expertSubServices;

    @Enumerated(value = EnumType.STRING)
    ExpertStatus expertStatus;

    @OneToOne
    Wallet wallet;

    @ManyToOne
    Admin admin;



    public Expert(String firstName, String lastName, String email,
                  String userName, String password, LocalDate dateOfSigningIn, byte[] personalPhoto,
                  ExpertStatus expertStatus,Integer stars) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.dateOfSigningIn = dateOfSigningIn;
        this.personalPhoto = personalPhoto;
        this.expertStatus = expertStatus;
        this.stars = stars;

    }
  /*  private boolean isValidPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$";
        return password.matches(pattern);
    }*/
}
