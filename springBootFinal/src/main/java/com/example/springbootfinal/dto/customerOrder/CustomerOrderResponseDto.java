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
    private String address;
    private StatusOfOrder statusOfOrder;

    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
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
