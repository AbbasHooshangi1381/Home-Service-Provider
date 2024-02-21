package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;

import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.ExpertService;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExpertServiceImpl implements ExpertService {
    ExpertRepository expertRepository;
    CustomerOrderRepository customerOrderRepository;
    SubDutyRepository subDutyRepository;
    SuggestionRepository suggestionRepository;

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
        Expert expert1 = expertRepository.findById(id).orElseThrow(() -> new NotFoundException("Expert not found"));
        ExpertStatus expertStatus = expert1.getExpertStatus();
        if (expertStatus.equals(ExpertStatus.CONFIRMED)) {
            throw new DuplicateException("you confirmed this email");
        }
        expert1.setExpertStatus(ExpertStatus.CONFIRMED);
        expertRepository.save(expert1);
        return expert1;
    }

    @Override
    public Expert changePassword(String oldPassword, String newPassword) {
        boolean present = expertRepository.findByPassword(newPassword).isPresent();
        if (present) {
            throw new NotValidException(" you have this password in database");
        }
        Expert expert = expertRepository.findByPassword(oldPassword).orElseThrow(() -> new NotFoundException(" i can not found this password"));
        expert.setPassword(newPassword);
        return expert;
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
        CustomerOrder customerOrder = customerOrderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("i can not found this order"));
        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.STARTED)) {
            throw new DuplicateException("you started this order");
        }
        customerOrder.setStatusOfOrder(StatusOfOrder.STARTED);
        customerOrderRepository.save(customerOrder);


    }

    @Override
    @Transactional
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
            Integer ratingReduction = (int) delayHours;
            Expert expert = suggestion.getExpert();
            Integer currentRating = expert.getStars();
            Integer updatedRating = currentRating - ratingReduction;
            if (updatedRating <= 0) {
                expert.setExpertStatus(ExpertStatus.NEW);
                expert.setStars(updatedRating);
                expertRepository.save(expert);
            } else {
                expert.setStars(updatedRating);
                expertRepository.save(expert);
            }
        }
        CustomerOrder customerOrder = suggestion.getCustomerOrder();
        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.DONE)) {
            throw new DuplicateException("you done this work at past");
        }
        customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
        customerOrderRepository.save(customerOrder);
    }


    public List<Expert> findAllExpertsByCriteria(Map<String, String> param) {
        Specification<Expert> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (param.containsKey("firstName") && param.get("firstName") != null) {
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + param.get("firstName").toLowerCase() + "%"));
            }
            if (param.containsKey("lastName") && param.get("lastName") != null) {
                predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + param.get("lastName").toLowerCase() + "%"));
            }
            if (param.containsKey("email") && param.get("email") != null) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + param.get("email").toLowerCase() + "%"));
            }

            query.distinct(true);

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return expertRepository.findAll(specification);
    }


    public List<Expert> findExpertByStar(Map<String, String> params) {

        Specification<Expert> specification = buildSpecification(params);
        return expertRepository.findAll(specification);
    }

    private Specification<Expert> buildSpecification(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (params.containsKey("rate")) {
                String rate = params.get("rate");
                Order order = "DESC".equals(rate)
                        ? criteriaBuilder.desc(root.get("stars"))
                        : criteriaBuilder.asc(root.get("stars"));
                query.orderBy(order);
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return expertRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("i can not found this email!"));
    }


}





