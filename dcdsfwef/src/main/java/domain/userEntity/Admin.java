package domain.userEntity;

import base.domin.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Admin extends BaseEntity<Integer> {
    String firstName;
    String lastName;
    String email;
    String userName;
    String password;
    LocalDate dateOfSigningIn;

    @OneToMany(mappedBy = "admin")
    List<Customer> customerList;

    @OneToMany(mappedBy = "admin")
    List<Expert> expertList;

    public Admin( String firstName, String lastName, String email, String userName, String password, LocalDate dateOfSigningIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.dateOfSigningIn = dateOfSigningIn;
    }
}
