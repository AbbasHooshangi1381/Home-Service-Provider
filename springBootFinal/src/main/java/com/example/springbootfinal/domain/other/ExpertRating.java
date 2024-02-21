package com.example.springbootfinal.domain.other;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExpertRating {
    Integer lowestRating;
    Integer highestRating;

    public ExpertRating(Integer lowestRating, Integer highestRating) {
        this.lowestRating = lowestRating;
        this.highestRating = highestRating;
    }
}
