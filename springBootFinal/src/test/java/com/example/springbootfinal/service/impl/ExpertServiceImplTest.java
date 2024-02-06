package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpertServiceImplTest {
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    ExpertService expertService;
    @Autowired
    DutyRepository dutyRepository;
    @Autowired
    SubDutyRepository subDutyRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    SuggestionService suggestionService;

    Expert save;
    Duty dutys;
    SubDuty subDutys;
    Customer customer;
    CustomerOrder customerOrder;

    @BeforeAll
    void setUpDuty() throws SQLException, IOException {
        Duty duty = new Duty(
                "electronic");
        dutys = dutyRepository.save(duty);
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";

        customer = customerService.saveCustomer(validFirstName, validLastName, validEmail, validUserName);

        SubDuty subDuty = new SubDuty(
                "gaz",
                500.00,
                "dafergergergrgrver",
                dutys);
        subDutys = subDutyRepository.save(subDuty);

        Expert expert = new Expert(
                "ali",
                "ahmadi",
                "okjggk@gmail.com",
                "pojguiu2",
                "aA53@dfr",
                LocalDate.now(),
                ExpertStatus.NEW,
                ImageInput.uploadProfilePicture("D:\\file of intelli j\\springBootFinal\\" +
                        "src\\main\\java\\com\\example\\springbootfinal\\image\\CamScanner 02-14-2022 12.36_2.jpg"));
        save = expertRepository.save(expert);
        assertNotNull(save);

        Integer id = customer.getId();
        assertNotNull(id);
        Integer id1 = subDutys.getId();
        assertNotNull(id1);
        String descriptionOfOrder = "you should it ! ";
        double proposedPrice = 8000.00;
        String timeOfWork = "1402/11/30";
        String address = "mashhad";
        StatusOfOrder waitingForSuggestExpert = StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
        customerOrder = customerOrderService.saveOrder(descriptionOfOrder,
                proposedPrice, timeOfWork, address, waitingForSuggestExpert, id, id1);
    }

    @Test
    @Order(1)
    void saveExperts() throws IOException {
        String name = "aldi";
        String lastname = "ahmfrfadi";
        String emails = "okdfejggk@gmail.com";
        String username = "pojgdduiu2";
       String location="D:\\file of intelli j\\springBootFinal\\" +
                        "src\\main\\java\\com\\example\\springbootfinal\\image\\CamScanner 02-14-2022 12.36_2.jpg";

         Expert expertss = expertService.saveExpert(name, lastname, emails, username, location);
         assertNotNull(expertss);
    }


    @Test
    @Transactional
    @Order(2)
    void changeStatusOfExpertByAdmin() {
         Integer id = save.getId();
        assertNotNull(id);
        expertService.changeStatusOfExpertByAdmin(id);

        Expert updatedExpert = expertRepository.findById(id).orElse(null);
        assert updatedExpert != null;
        Assertions.assertEquals(ExpertStatus.CONFIRMED, updatedExpert.getExpertStatus());
    }

    @Test
    @Transactional
    @Order(3)
    void findByUserNameAndPassword() {
        String email = save.getEmail();
        Optional<Expert> byEmail = expertRepository.findByEmail(email);
        String password = byEmail.get().getPassword();
        String userName = byEmail.get().getUserName();
        Optional<Expert> byUserNameAndPassword = expertService.findByUserNameAndPassword(userName, password);
        assertTrue(byUserNameAndPassword.isPresent());
        Optional<Expert> byUserNameAndPassword1 = expertService.findByUserNameAndPassword("aaaaaa", "bbbbbbb");
        assertFalse(byUserNameAndPassword1.isPresent());

    }

    @Test
    @Transactional
    @Order(4)
    void changePassword() {
        Expert experts = expertRepository.findByEmail("okjggk@gmail.com").get();
        Integer id = experts.getId();
        assertNotNull(id);
        String newPassword = "newPassword123";
         Expert expert = expertService.changePassword(id, newPassword);
        assertNotNull(expert);
        String changedPassword = experts.getPassword();
        assertNotNull(changedPassword);
        assertEquals(newPassword, changedPassword);
         Expert expert1 = expertService.changePassword(2, newPassword);
        assertNotNull(expert1);
    }

    @Test
    @Order(5)
    void saveImageByIdToSystem() throws IOException {
        Integer id = save.getId();
        byte[] bytes = expertService.saveImageByIdToSystem(id);
        assertNotNull(bytes);
        boolean imageExists = ImageInput.isImageExists("D:\\منابع مکتب شریف\\final-project\\images\\New folder\\New folder"
                + 22 + ".jpg");
        assertTrue(imageExists);
    }

/*    @Test
    @Order(6)
    void sendOfferForSubDuty() throws SQLException {
        Integer id = save.getId();
        assertNotNull(id);
        Integer id1 = customerOrder.getId();
        assertNotNull(id1);
        suggestionService.sendOfferForSubDuty(id, id1, 5000.00, "1402/11/19");
    }*/

    @Test
    @Order(7)
    void customerOrderList() {
        List<CustomerOrder> customerOrders = suggestionService.customerOrderList();
        assertNotNull(customerOrders);
    }

/*    @Test
    @Transactional
    @Order(3)
    void changeStatusOfOrderByCustomerStarted() {
        Integer id = customerOrder.getId();
        assertNotNull(id);
        expertService.changeStatusOfOrderByCustomerStarted(id);
    }*/

/*    @Test
    @Transactional
    @Order(4)
    void changeStatusOfOrderByCustomerToFinish() {
        Integer id = customerOrder.getId();
        assertNotNull(id);
        expertService.changeStatusOfOrderByCustomerToFinish(id);
    }*/
}