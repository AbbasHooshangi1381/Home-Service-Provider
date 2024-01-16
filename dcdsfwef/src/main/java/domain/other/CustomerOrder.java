package domain.other;

import base.domin.BaseEntity;
import domain.enumurations.StatusOfOrder;
import domain.serviceEntity.SubService;
import domain.userEntity.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerOrder extends BaseEntity<Integer> {

    String descriptionOfOrder;

    Double ProposedPrice;

    String descriptionOfWork;

    LocalDate timeOfDoing;

    String address;

    @ManyToOne
    SubService subService;

    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    List<Suggestion> suggestionList;

    @Enumerated(value = EnumType.STRING)
    StatusOfOrder statusOfOrder;
}
