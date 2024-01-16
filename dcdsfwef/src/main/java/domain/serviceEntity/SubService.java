package domain.serviceEntity;

import base.domin.BaseEntity;
import domain.other.CustomerOrder;
import domain.userEntity.Expert;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SubService extends BaseEntity<Integer> {

    String SubServiceName;
    Double price;
    String description;

    @ManyToOne
    @ToString.Exclude
    Service service;

    @ManyToOne
    Expert expert;

    @OneToMany(mappedBy = "subService")
    List<CustomerOrder> customerOrderList;

    public SubService( String subServiceName, Double price, String description, Service service) {
        SubServiceName = subServiceName;
        this.price = price;
        this.description = description;
        this.service = service;
    }

    @Override
    public String toString() {
        return "SubService{" +
                "SubServiceName='" + SubServiceName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
