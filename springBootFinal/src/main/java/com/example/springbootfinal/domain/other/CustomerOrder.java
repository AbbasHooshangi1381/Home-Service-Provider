package com.example.springbootfinal.domain.other;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
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
public class CustomerOrder extends BaseEntity<Integer> {

    String descriptionOfOrder;

    Double proposedPrice;

    String timeOfDoing;

    String address;

    @ManyToOne
    SubDuty subService;

    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    List<Suggestion> suggestionList;

    @Enumerated(value = EnumType.STRING)
    StatusOfOrder statusOfOrder;

    public CustomerOrder(String descriptionOfOrder, Double proposedPrice, String timeOfDoing,
                         String address, SubDuty subService, Customer customer, StatusOfOrder statusOfOrder) {
        this.descriptionOfOrder = descriptionOfOrder;
        this.proposedPrice = proposedPrice;
        this.timeOfDoing = timeOfDoing;
        this.address = address;
        this.subService = subService;
        this.customer = customer;
        this.statusOfOrder = statusOfOrder;
    }

    public CustomerOrder( String descriptionOfOrder, Double proposedPrice, String timeOfDoing,
                         String address, StatusOfOrder statusOfOrder) {
        this.descriptionOfOrder = descriptionOfOrder;
        this.proposedPrice = proposedPrice;
        this.timeOfDoing = timeOfDoing;
        this.address = address;
        this.statusOfOrder = statusOfOrder;
    }
}
