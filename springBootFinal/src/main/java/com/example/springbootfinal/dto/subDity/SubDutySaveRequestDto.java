package com.example.springbootfinal.dto.subDity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SubDutySaveRequestDto {
    @NotNull
    @Min(0)
    Integer dutyId;
    @NotNull
    String subServiceName;
    @NotNull
    @Min(0)
    Double priceOfSubDuty;
    @NotNull
    String description;

    public Integer getDutyId() {
        return dutyId;
    }


    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }

    public Double getPriceOfSubDuty() {
        return priceOfSubDuty;
    }


    public String getDescription() {
        return description;
    }

}
