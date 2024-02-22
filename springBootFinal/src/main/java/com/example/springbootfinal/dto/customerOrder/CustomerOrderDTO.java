package com.example.springbootfinal.dto.customerOrder;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CustomerOrderDTO {
    @NotBlank
    private String descriptionOfOrder;

    @NotNull
    @Positive
    private Double proposedPrice;

    @NotBlank
    private String timeOfWork;

    @NotBlank
    private String address;

    @NotNull
    @Positive
    private Integer customerId;

    @NotNull
    @Positive
    private Integer subDutyId;
    @NotNull
    private StatusOfOrder statusOfOrder;

    public String getDescriptionOfOrder() {
        return descriptionOfOrder;
    }


    public double getProposedPrice() {
        return proposedPrice;
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

    public Integer getCustomerId() {
        return customerId;
    }


    public Integer getSubDutyId() {
        return subDutyId;
    }


    public StatusOfOrder getStatusOfOrder() {
        return statusOfOrder;
    }

    public void setStatusOfOrder(StatusOfOrder statusOfOrder) {
        this.statusOfOrder = statusOfOrder;
    }
}
