package com.example.springbootfinal.dto.subDity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SubDutyResponseDto {
    String subServiceName;
    Double priceOfSubDuty;
    String description;

    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }

    public Double getPriceOfSubDuty() {
        return priceOfSubDuty;
    }

    public void setPriceOfSubDuty(Double priceOfSubDuty) {
        this.priceOfSubDuty = priceOfSubDuty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
