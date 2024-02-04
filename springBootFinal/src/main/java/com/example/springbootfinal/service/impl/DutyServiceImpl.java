package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.service.DutyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyServiceImpl implements DutyService {
    DutyRepository dutyRepository;
    public DutyServiceImpl(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    @Override
    public Duty saveServiceByAdmin(String dutyName) {
        Optional<Duty> duty1 = dutyRepository.findByName(dutyName);
        if (duty1.isPresent()) {
            System.out.println("i have this service");
        }
            Duty duty = new Duty(dutyName);
            dutyRepository.save(duty);
            System.out.println("service added to data base ! ");
            return duty;
    }

    @Override
    public List<SubDuty> showSubDuty(String dutyName) {
        Optional<Duty> electronic = dutyRepository.findByName(dutyName);
        if (electronic.isEmpty()){
            throw new NotFoundException("nothing!");
        }
        return electronic.get().getSubServiceList();
    }
}
