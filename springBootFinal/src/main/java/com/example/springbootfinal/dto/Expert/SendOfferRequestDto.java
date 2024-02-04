package com.example.springbootfinal.dto.Expert;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SendOfferRequestDto {
    private Integer expertId;
    private Integer customerOrderId;
    private double suggestionPrice;
    private String timeOfWork;

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public double getSuggestionPrice() {
        return suggestionPrice;
    }

    public void setSuggestionPrice(double suggestionPrice) {
        this.suggestionPrice = suggestionPrice;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }
}
