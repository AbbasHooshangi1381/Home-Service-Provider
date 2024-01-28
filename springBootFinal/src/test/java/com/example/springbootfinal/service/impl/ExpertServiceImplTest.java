package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.ExpertService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ExpertServiceImplTest {

    ExpertRepository expertRepository;
    ExpertService expertService;

    Expert expert;

    @BeforeEach
    void saveExpert() throws IOException {
        String firstName = "ali";
        String lastName = "ahmadi";
        String email = "okjggk@gmail.com";
        String userName = "pojguiu2";
        LocalDate timeOfSignIn = LocalDate.now();
        String filePath = "D:\\file of intelli j\\dcdsfwef\\src\\main\\java\\image\\CamScanner 02-14-2022 12.36_2.jpg";

         expertService.saveExpert(firstName, lastName, email, userName, timeOfSignIn, filePath);
    }

    @Test
    void changeStatusOfExpertByAdmin() {
        Integer expertId = 1;

        Expert existingExpert = new Expert();
        existingExpert.setId(expertId);
        existingExpert.setExpertStatus(ExpertStatus.CONFIRMED);
        expertRepository.save(existingExpert);

        expertService.changeStatusOfExpertByAdmin(expertId);

        Expert updatedExpert = expertRepository.findById(expertId).orElse(null);
        assert updatedExpert != null;
        Assertions.assertEquals(ExpertStatus.CONFIRMED, updatedExpert.getExpertStatus());
    }
}