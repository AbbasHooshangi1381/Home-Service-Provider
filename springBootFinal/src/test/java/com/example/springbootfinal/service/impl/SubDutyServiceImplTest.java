package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.SubDutyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubDutyServiceImplTest {
    @Mock
    private DutyRepository dutyRepository;

    @Mock
    private SubDutyRepository subDutyRepository;

    @InjectMocks
    private SubDutyService subDutyService;

    @Test
    void saveSubDutyByAdminWhenDutyExistsAndSubDutyDoesNotExist() {
        Integer dutyId = 123;
        String subServiceName = "homeElectronic";

        Duty duty = new Duty();
        when(dutyRepository.findById(eq(dutyId))).thenReturn(Optional.of(duty));

        when(subDutyRepository.findBySubServiceName(eq(subServiceName))).thenReturn(Optional.empty());

        subDutyService.saveSubDutyByAdmin(dutyId, subServiceName);

        verify(dutyRepository, times(1)).findById(eq(dutyId));
        verify(subDutyRepository, times(1)).findBySubServiceName(eq(subServiceName));

    }

    @Test
    void saveSubDutyByAdminWhenDutyDoesNotExist() {
        Integer dutyId = 123;
        String subServiceName = "homeElectronic";
        when(dutyRepository.findById(eq(dutyId))).thenReturn(Optional.empty());
        subDutyService.saveSubDutyByAdmin(dutyId, subServiceName);
        verify(dutyRepository, times(1)).findById(eq(dutyId));
        verify(subDutyRepository, never()).findBySubServiceName(any());


    }

    @Test
    void saveSubDutyByAdminWhenSubDutyExists() {
        Integer dutyId = 123;
        String subServiceName = "homeElectronic";

        Duty duty = new Duty();
        when(dutyRepository.findById(eq(dutyId))).thenReturn(Optional.of(duty));

        SubDuty subDuty = new SubDuty();
        when(subDutyRepository.findBySubServiceName(eq(subServiceName))).thenReturn(Optional.of(subDuty));

        subDutyService.saveSubDutyByAdmin(dutyId, subServiceName);
    }

    @Test
    void changeDescriptionOfSubDuty() {
        Integer subDutyId = 1;
        String newDescription = "mamad";

        SubDuty subDuty = new SubDuty();
        subDuty.setId(subDutyId);
        subDuty.setDescription(newDescription);

        when(subDutyRepository.findById(subDutyId)).thenReturn(Optional.of(subDuty));

        subDutyService.changeDescriptionOfSubDuty(subDutyId, newDescription);

        assertEquals(newDescription, subDuty.getDescription());
        verify(subDutyRepository, times(1)).findById(subDutyId);
        verify(subDutyRepository, times(1)).save(subDuty);
    }

    @Test
    void changePriceOfSubDutyByAdmin() {
        Integer subDutyId = 1;
        Double newPrice = 10.99;

        SubDuty subDuty = new SubDuty();
        subDuty.setPrice(newPrice);
        Mockito.when(subDutyRepository.findById(subDutyId)).thenReturn(Optional.of(subDuty));

        subDutyService.changePriceOfSubDutyByAdmin(subDutyId,newPrice);

        assertEquals(newPrice,subDuty.getPrice());
        verify(subDutyRepository, times(1)).findById(subDutyId);
        verify(subDutyRepository, times(1)).save(subDuty);

    }

}

