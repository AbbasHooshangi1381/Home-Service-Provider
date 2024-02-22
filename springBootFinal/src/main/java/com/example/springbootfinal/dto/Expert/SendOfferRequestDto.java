package com.example.springbootfinal.dto.Expert;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SendOfferRequestDto {
    @NotNull
    @Min(0)
    Integer customerOrderId;
    @NotNull
    @Min(0)
    Double suggestionPrice;
    @NotNull
    String timeOfWork;
    @NotNull
    String durationTimeOfWork;

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }


    public Double getSuggestionPrice() {
        return suggestionPrice;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

    public String getDurationTimeOfWork() {
        return durationTimeOfWork;
    }

}
