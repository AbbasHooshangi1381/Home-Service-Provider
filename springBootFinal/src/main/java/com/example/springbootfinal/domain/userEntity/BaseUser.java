package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.enumurations.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class BaseUser extends BaseEntity<Integer> implements UserDetails {
    String firstName;
    String lastName;
    String email;
    String userName;
    String password;
    LocalDate dateOfSigningIn;
    @Enumerated(EnumType.STRING)
    Role role;
    Boolean enabled;

    public BaseUser(String firstName,
                    String lastName, String email, String userName, String password,
                    LocalDate dateOfSigningIn, Boolean enabled,Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        if (!isValidPassword(password)) {
            System.out.println("Invalid password format!");
        } else {
            this.password = password;
        }
        this.dateOfSigningIn = dateOfSigningIn;
        this.enabled=enabled;
        this.role=role;
    }

    private boolean isValidPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$";
        return password.matches(pattern);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    public String getPassword() {
        return password;
    }

}
