package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.ExpertService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.example.springbootfinal.validation.Validation.generateRandomPassword;

@Service
@Transactional
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    SubDutyRepository subDutyRepository;
    @Autowired
    SuggestionRepository suggestionRepository;


    @Override
    public Expert saveExpert(String firstName, String lastName, String email, String userName, String filePath) throws IOException {

        String password = generateRandomPassword();
        Optional<Expert> byEmail = expertRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new DuplicateException("ایمیل تکراری است.");
        }
        byte[] imageData = ImageInput.uploadProfilePicture(filePath);
        ExpertStatus expertStatus = ExpertStatus.NEW;
        Integer star = 0;
        LocalDate timeOfSignIn = LocalDate.now();

        Expert expertSave = new Expert(firstName, lastName, email,
                userName, password, timeOfSignIn, imageData, star, expertStatus);
        return expertRepository.save(expertSave);
    }

    @Override
    public Optional<Expert> findByUserNameAndPassword(String username, String password) {
        Expert expert = expertRepository.findByUserNameAndPassword(username, password).orElseThrow(() -> new NotFoundException(" i can not found this expert"));
        if (expert != null) {
            System.out.println("you are in system ");
        }
        return Optional.ofNullable(expert);
    }

    @Override
    public Expert changeStatusOfExpertByAdmin(Integer id) {
        Expert expert1 = expertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Expert not found"));
        expert1.setExpertStatus(ExpertStatus.CONFIRMED);
        expertRepository.save(expert1);
        return expert1;
    }

    @Override
    public Expert changePassword(Integer id, String newPassword) {
        Expert expert1 = expertRepository.findById(id).orElseThrow(() -> new NotFoundException(" i can not found this expert"));
        if (expert1 != null) {
            expert1.setPassword(newPassword);
            return expertRepository.save(expert1);
        }
        return expert1;
    }

    @Override
    public byte[] saveImageByIdToSystem(Integer id) throws IOException {
        Expert expert = expertRepository.findById(id).orElseThrow(FileNotFoundException::new);
        byte[] personalPhoto = expert.getPersonalPhoto();
        FileOutputStream fos = new FileOutputStream("D:\\منابع مکتب شریف\\final-project\\images\\New folder\\New folder" + 22 + ".jpg");
        fos.write(personalPhoto);
        fos.close();
        return personalPhoto;
    }

    @Override
    public void changeStatusOfOrderByExpertStarted(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        if (byId.isEmpty()) {
            throw new NotFoundException("i dont have this order ");
        } else {
            byId.ifPresent(customerOrder -> {
                customerOrder.setStatusOfOrder(StatusOfOrder.STARTED);
                customerOrderRepository.save(customerOrder);
            });
        }
    }

    @Override
    public void changeStatusOfOrderByCustomerToFinish(Integer suggestionId, String timeOfFinishingWork) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId).orElseThrow(() -> new NotFoundException(" i can not found this suggestion"));

        String timeOfStartingWork = suggestion.getTimeOfStartingWork();
        String durationTimeOfWork = suggestion.getDurationTimeOfWork();

        LocalDateTime startWorkTime = LocalDateTime.parse(timeOfStartingWork, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalTime durationTime = LocalTime.parse(durationTimeOfWork);
        LocalDateTime expectedFinishTime = startWorkTime.plusHours(durationTime.getHour()).plusMinutes(durationTime.getMinute()).plusSeconds(durationTime.getSecond());

        LocalDateTime actualFinishTime = LocalDateTime.parse(timeOfFinishingWork, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        if (actualFinishTime.isAfter(expectedFinishTime)) {
            long delayHours = ChronoUnit.HOURS.between(actualFinishTime, expectedFinishTime);
            int ratingReduction = (int) delayHours;

            Expert expert = suggestion.getExpert();
            Integer currentRating = expert.getStars();
            int updatedRating = Math.max(0, currentRating - ratingReduction);
            if (updatedRating <= 0) {
                expert.setExpertStatus(ExpertStatus.NEW);
                expertRepository.save(expert);
                throw new NotValidException(" your account is disAble");

            } else {
                expert.setStars(updatedRating);
                expertRepository.save(expert);
            }
        }
        CustomerOrder customerOrder = suggestion.getCustomerOrder();
        customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
        customerOrderRepository.save(customerOrder);
    }
}




