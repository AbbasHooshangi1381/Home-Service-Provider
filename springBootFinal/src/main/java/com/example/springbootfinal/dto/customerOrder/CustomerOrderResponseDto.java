package com.example.springbootfinal.dto.customerOrder;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString

public class CustomerOrderResponseDto {
    private Integer Id;
    private String descriptionOfOrder;
    private Double proposedPrice;
    private String timeOfWork;
    private String address;
    private StatusOfOrder statusOfOrder;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescriptionOfOrder() {
        return descriptionOfOrder;
    }

    public void setDescriptionOfOrder(String descriptionOfOrder) {
        this.descriptionOfOrder = descriptionOfOrder;
    }

    public Double getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(Double proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StatusOfOrder getStatusOfOrder() {
        return statusOfOrder;
    }

    public void setStatusOfOrder(StatusOfOrder statusOfOrder) {
        this.statusOfOrder = statusOfOrder;
    }
}
