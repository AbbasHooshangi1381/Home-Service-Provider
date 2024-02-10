package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.other.Wallet;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.ExpertService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @Autowired
    WalletRepository walletRepository;

    @Override
    public Expert saveExpert(String firstName, String lastName, String email, String userName, String filePath) throws IOException {
        String password = generateRandomPassword();
        Optional<Expert> byEmail = expertRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new DuplicateException("ایمیل تکراری است.");
        }
        Wallet wallet = new Wallet(500.00);
        Wallet save = walletRepository.save(wallet);
        byte[] imageData = ImageInput.uploadProfilePicture(filePath);
        ExpertStatus expertStatus = ExpertStatus.NEW;
        Integer star = 0;
        LocalDate timeOfSignIn = LocalDate.now();
        Expert expertSave = new Expert(firstName, lastName, email,
                userName, password, timeOfSignIn, imageData, star, expertStatus,save);
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
        Suggestion suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new NotFoundException("i can not found this suggestion"));
        String timeOfStartingWork = suggestion.getTimeOfStartingWork();
        String durationTimeOfWork = suggestion.getDurationTimeOfWork();
        LocalDateTime startWorkTime = LocalDateTime.parse(timeOfStartingWork, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalTime durationTime = LocalTime.parse(durationTimeOfWork, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime expectedFinishTime = startWorkTime
                .plusHours(durationTime.getHour())
                .plusMinutes(durationTime.getMinute())
                .plusSeconds(durationTime.getSecond());
        LocalDateTime actualFinishTime = LocalDateTime.parse(timeOfFinishingWork, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (actualFinishTime.isAfter(expectedFinishTime)) {
            long delayHours = ChronoUnit.HOURS.between(expectedFinishTime, actualFinishTime);
            int ratingReduction = (int) delayHours;
            Expert expert = suggestion.getExpert();
            Integer currentRating = expert.getStars();
            int updatedRating = Math.max(0, currentRating - ratingReduction);
            if (updatedRating <= 0) {
                expert.setExpertStatus(ExpertStatus.NEW);
                expertRepository.save(expert);
                throw new NotValidException("your account is disabled");
            } else {
                expert.setStars(updatedRating);
                expertRepository.save(expert);
            }
        }
        CustomerOrder customerOrder = suggestion.getCustomerOrder();
        customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
        customerOrderRepository.save(customerOrder);
    }
    @Override
    public List<Suggestion> findByCustomerOrderIdOrderByExpertStarsDesc(Integer customerOrderId) {
         CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).get();
         Customer customer = customerOrder.getCustomer();
        final List<Suggestion> expertsByOrderIdOrderByStarDesc =
                expertRepository.findExpertsByOrderIdOrderByStarDesc(customer);
        if (expertsByOrderIdOrderByStarDesc.isEmpty()) {
            throw new NotFoundException("i can not find this customer order");
        } else {
            for (Suggestion suggestion : expertsByOrderIdOrderByStarDesc) {
                System.out.println(suggestion);
                break;
            }
        }
        return expertsByOrderIdOrderByStarDesc;
    }
        public List<Expert> findAllExpertsByCriteria(Map<String, String> param) {
            Specification<Expert> specification = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (param.containsKey("firstname") && param.get("firstname") != null) {
                    predicates.add(criteriaBuilder.like(root.get("firstname"), "%" + param.get("firstname") + "%"));
                }
                if (param.containsKey("lastname") && param.get("lastname") != null) {
                    predicates.add(criteriaBuilder.like(root.get("lastname"), "%" + param.get("lastname") + "%"));
                }

                if (param.containsKey("subDuty") && param.get("subDuty") != null) {
                    predicates.add(criteriaBuilder.equal(root.join("subDuty").get("subDuty"), param.get("subDuty")));
                }
                List<Order> orderList = new ArrayList<>();
                if (param.containsKey("stars") && param.get("stars") != null) {
                    if (param.get("stars").equalsIgnoreCase("ASC")) {
                        orderList.add(criteriaBuilder.asc(root.get("stars")));
                    } else if (param.get("stars").equalsIgnoreCase("DESC")) {
                        orderList.add(criteriaBuilder.desc(root.get("stars")));
                    }
                }
                if (!orderList.isEmpty()) {
                    query.orderBy(orderList);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };

            return expertRepository.findAll(specification);
        }
    }





