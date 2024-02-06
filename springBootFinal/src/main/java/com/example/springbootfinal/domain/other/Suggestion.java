package com.example.springbootfinal.domain.other;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.userEntity.Expert;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Suggestion extends BaseEntity<Integer> {

    String timeOfSendSuggestion;

    Double suggestionPrice;

    String timeOfStartingWork;

    String durationTimeOfWork;

    @ManyToOne
    CustomerOrder customerOrder;

    @ManyToOne
    Expert expert;

    public Suggestion(String timeOfSendSuggestion, Double suggestionPrice,
                      String timeOfStartingWork, String durationTimeOfWork) {
        this.timeOfSendSuggestion = timeOfSendSuggestion;
        this.suggestionPrice = suggestionPrice;
        this.timeOfStartingWork = timeOfStartingWork;
        this.durationTimeOfWork = durationTimeOfWork;
    }
}
