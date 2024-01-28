package com.example.springbootfinal.domain.other;

import com.example.springbootfinal.baseDomain.BaseEntity;
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

    LocalDate timeOfSendSuggestion;

    Double suggestionPrice;

    LocalDate timeOfStartingWork;

    String durationTimeOfWork;

    @ManyToOne
    CustomerOrder customerOrder;

    public Suggestion(LocalDate timeOfSendSuggestion, Double suggestionPrice,
                      LocalDate timeOfStartingWork, String durationTimeOfWork) {
        this.timeOfSendSuggestion = timeOfSendSuggestion;
        this.suggestionPrice = suggestionPrice;
        this.timeOfStartingWork = timeOfStartingWork;
        this.durationTimeOfWork = durationTimeOfWork;
    }
}
