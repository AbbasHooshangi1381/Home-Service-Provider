package domain.other;

import base.domin.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Suggestion extends BaseEntity<Integer> {

    LocalDate timeOfSendSuggestion;

    Double suggestionPrice;

    LocalDate timeOfStartingWork;

    LocalDate durationTimeOfWork;

    @ManyToOne
    CustomerOrder customerOrder;


}
