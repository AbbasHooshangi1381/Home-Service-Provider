package com.example.springbootfinal.dto.customer;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryOfOrderDto {
    Integer Id;
    String statusOfOrder;

}
