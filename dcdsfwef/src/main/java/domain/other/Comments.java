package domain.other;

import base.domin.BaseEntity;
import domain.userEntity.Customer;
import domain.userEntity.Expert;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comments extends BaseEntity<Integer> {

    String comments;

    @ManyToOne
    Customer customer;
}
