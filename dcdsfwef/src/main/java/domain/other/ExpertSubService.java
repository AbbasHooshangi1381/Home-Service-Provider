package domain.other;

import base.domin.BaseEntity;
import domain.serviceEntity.SubService;
import domain.userEntity.Expert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpertSubService extends BaseEntity<Integer> {

    @ManyToOne
    Expert expert;

    @ManyToOne
    SubService subService;
}
