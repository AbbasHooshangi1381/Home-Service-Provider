package com.example.springbootfinal.domain.serviceEntity;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Expert;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SubDuty extends BaseEntity<Integer> {
    @Column(unique = true)
    String subServiceName;
    Double price;
    String description;

    @ManyToOne
    @ToString.Exclude
    Duty service;

    @ManyToMany
    List<Expert> experts;

    @OneToMany(mappedBy = "subService")
    List<CustomerOrder> customerOrderList;

    public SubDuty(String subServiceName, Double price, String description, Duty service) {
        this.subServiceName = subServiceName;
        this.price = price;
        this.description = description;
        this.service = service;
    }

    @Override
    public String toString() {
        return "SubService{" +
                "SubServiceName='" + subServiceName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
