package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.service.DutyService;
import org.springframework.stereotype.Service;

@Service
public class DutyServiceImpl implements DutyService {

    DutyRepository dutyRepository;

    public DutyServiceImpl(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    @Override
    public void saveServiceByAdmin(String dutyName) {
        boolean byName = dutyRepository.existsByName(dutyName);

        if (byName == true) {
            System.out.println("i have this service");
        } else {
            Duty duty = new Duty(dutyName);
            dutyRepository.save(duty);
            System.out.println("service added to data base ! ");
        }
    }


}
