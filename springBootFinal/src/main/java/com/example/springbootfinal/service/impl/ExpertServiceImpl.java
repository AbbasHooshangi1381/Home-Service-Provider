package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.ExpertService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.springbootfinal.domain.enumurations.StatusOfOrder.WAITING_FOR_EXPERT_SUGGESTIONS;
import static com.example.springbootfinal.domain.enumurations.StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
import static com.example.springbootfinal.service.impl.AdminServiceImpl.validationEmails;
import static com.example.springbootfinal.service.impl.AdminServiceImpl.validationNames;
import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;
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
    public Expert saveExpert(String firstName, String lastName, String email, String userName, LocalDate timeOfSignIn, String filePath) throws IOException {

        String validatedFirstName = validationNames(firstName);
        String validatedLastName = validationNames(lastName);
        String validatedEmail = validationEmails(email);
        String password = generateRandomPassword();

        if (expertRepository.existsByEmail(validatedEmail)) {
            throw new RuntimeException("ایمیل تکراری است.");
        }
        byte[] imageData = ImageInput.uploadProfilePicture(filePath);
        ExpertStatus expertStatus = ExpertStatus.NEW;
        Integer star = 0;
        Expert expert = new Expert(validatedFirstName, validatedLastName, validatedEmail,
                userName, password, timeOfSignIn, imageData, star, expertStatus);

        expertRepository.save(expert);

        return expert;
    }

    @Override
    public Optional<Expert> findByUserNameAndPassword(String username, String password) {
        Optional<Expert> byUserNameAndPassword = expertRepository.findByUserNameAndPassword(username, password);
        if (byUserNameAndPassword.isPresent()) {
            System.out.println("you are in system ");
        } else {
            System.out.println("you are not in system ");
        }
        return byUserNameAndPassword;
    }


    @Override
    public void changeStatusOfExpertByAdmin(Integer id) {
        Optional<Expert> optionalExpert = expertRepository.findById(id);
        if (optionalExpert.isPresent()) {
            Expert expert = optionalExpert.get();
            expert.setExpertStatus(ExpertStatus.CONFIRMED);
            expertRepository.save(expert);
        } else {
            System.out.println("You cannot change the ExpertStatus.");
        }
    }


    @Override
    public Boolean changePassword(Integer id, String newPassword) {
        Optional<Expert> optionalExpert = expertRepository.findById(id);

        if (optionalExpert.isPresent()) {
            Expert expert = optionalExpert.get();
            expert.setPassword(newPassword);
            expertRepository.save(expert);
            return true;
        } else {
            System.out.println("You cannot change the password. Expert with ID " + id + " does not exist.");
            return false;
        }
    }

    @Override
    public byte[] saveImageByIdToSystem(Integer id) {
        Expert expert = expertRepository.findById(id).orElse(null);

        assert expert != null;
        byte[] personalPhoto = expert.getPersonalPhoto();

        try (FileOutputStream fos = new FileOutputStream("D:\\منابع مکتب شریف\\final-project\\images\\New folder\\New folder"
                + 22 + ".jpg")) {
            fos.write(personalPhoto);
        } catch (IOException e) {
            System.err.println(" i can not create image !");
            e.printStackTrace();
        }

        return personalPhoto;
    }

    @Override
    public void sendOfferForSubDuty(Integer expertId, Integer customerOrderId, double suggestionPrice, String timeOfWork) throws SQLException {
        Optional<Expert> byId = expertRepository.findById(expertId);
        Optional<CustomerOrder> customerOrderOpt = customerOrderRepository.findById(customerOrderId);
        if (customerOrderOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid customerOrderId");
        }

        CustomerOrder customerOrder = customerOrderOpt.get();
        Suggestion suggestion = new Suggestion();
        Double proposedPrice = customerOrder.getProposedPrice();
        //   Double validatedPrice ;
        if (byId.isPresent()) {
            if (suggestionPrice >= proposedPrice) {
                Double validatedPrice = proposedPrice;


                suggestion.setSuggestionPrice(validatedPrice);
                String time = checkAndRegisterTimeOfLoan(timeOfWork);
                suggestion.setTimeOfSendSuggestion(LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                suggestion.setDurationTimeOfWork("2hours");
                suggestionRepository.save(suggestion);
                customerOrder.setStatusOfOrder(WAITING_FOR_SELECT_EXPERT);
                customerOrderRepository.save(customerOrder);
            }
        }
    }

    @Override
    public List<CustomerOrder> customerOrderList() {
        List<CustomerOrder> all = customerOrderRepository.findAll();

        List<CustomerOrder> filteredOrders = all.stream()
                .filter(order ->
                        order.getStatusOfOrder().equals(WAITING_FOR_EXPERT_SUGGESTIONS) ||
                                order.getStatusOfOrder().equals(WAITING_FOR_SELECT_EXPERT)
                )
                .collect(Collectors.toList());

        for (CustomerOrder order : filteredOrders) {
            System.out.println(order);
        }

        return filteredOrders;
    }

}
