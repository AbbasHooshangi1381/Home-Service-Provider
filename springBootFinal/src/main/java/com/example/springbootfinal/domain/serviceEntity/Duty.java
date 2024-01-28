package com.example.springbootfinal.domain.serviceEntity;

import com.example.springbootfinal.baseDomain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Duty extends BaseEntity<Integer> {

    private String name;

    @OneToMany(mappedBy ="service" )
    List<SubDuty>subServiceList;


    public Duty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                '}';
    }
}
