package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.specification.RequestSpecificationDto;
import com.example.springbootfinal.dto.specification.SearchSpecificationDto;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FilterSpecificationImpl<T> {
    public Specification<T> getSearchSpecificationDto(List<SearchSpecificationDto> searchSpecificationDto,
                                                      RequestSpecificationDto.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchSpecificationDto search : searchSpecificationDto) {
                switch (search.getOperation()) {
                    case EQUAL -> {
                        Predicate equal = criteriaBuilder.equal(root.get(search.getColumn()), search.getValue());
                        predicates.add(equal);
                        break;
                    }
                    case LIKE -> {
                        Predicate like = criteriaBuilder.like(root.get(search.getColumn()),"%"+ search.getValue()+"%");
                        predicates.add(like);
                        break;
                    }
                    case IN -> {
                         String[] split = search.getValue().split(",");
                         Predicate in = root.get(search.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                        break;
                    }
                    case LESS_THAN -> {
                        Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(root.get(search.getColumn()), search.getValue());
                        predicates.add(lessThanOrEqualTo);
                        break;
                    }
                    case GREATER_THAN -> {
                        Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(root.get(search.getColumn()), search.getValue());
                        predicates.add(greaterThanOrEqualTo);
                        break;
                    }
                    case BETWEEN -> {
                        String[] split = search.getValue().split(",");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime startDate = LocalDateTime.parse(split[0], formatter);
                            LocalDateTime endDate = LocalDateTime.parse(split[1], formatter);

                            Path<String> datePath = root.get(search.getColumn());
                            Predicate startPredicate = criteriaBuilder.greaterThanOrEqualTo(datePath, split[0]);
                            Predicate endPredicate = criteriaBuilder.lessThanOrEqualTo(datePath, split[1]);
                            Predicate betweenPredicate = criteriaBuilder.and(startPredicate, endPredicate);

                            predicates.add(betweenPredicate);
                            break;

                    }
                    case BetweenDateRegister -> {
                        String[] split = search.getValue().split(",");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate startDate = LocalDate.parse(split[0], formatter);
                        LocalDate endDate = LocalDate.parse(split[1], formatter);

                        Path<LocalDate> datePath = root.get(search.getColumn());
                        Predicate startPredicate = criteriaBuilder.greaterThanOrEqualTo(datePath, startDate);
                        Predicate endPredicate = criteriaBuilder.lessThanOrEqualTo(datePath, endDate);
                        Predicate betweenPredicate = criteriaBuilder.and(startPredicate, endPredicate);

                        predicates.add(betweenPredicate);
                        break;

                    }
                    case JOIN -> {
                        Predicate join = criteriaBuilder.equal(root.join(search.getJoinTable()).get(search.getColumn()), search.getValue());
                        predicates.add(join);
                        break;
                    }

                    case JoinBySubDutyName -> {
                        Predicate join = criteriaBuilder.equal(root.join("subService").get("subServiceName"), search.getValue());
                        predicates.add(join);
                        break;
                    }
                    case CUSTOMER_ORDER_COUNT -> {
                        Subquery<Long> subquery = query.subquery(Long.class);
                        Root<CustomerOrder> subRoot = subquery.from(CustomerOrder.class);

                        // براساس فرض شما که مشتری به وسیله ایدی یکتا شناخته می‌شود
                        subquery.select(criteriaBuilder.count(subRoot));
                        subquery.where(criteriaBuilder.equal(subRoot.get("customer").get("id"), Integer.parseInt(search.getValue())));

                        // Get the resulting count as a selectable expression (scalar selection)
                        Expression<Long> customerOrdersCount = subquery.getSelection();

                        // We create a predicate to return a boolean condition that checks
                        // if the customer has orders greater than 0, meaning at least one order exists
                        Predicate hasOrders = criteriaBuilder.greaterThan(customerOrdersCount, 0L);

                        // Add the predicate to the list of predicates
                        predicates.add(hasOrders);
                        break;
                    }



                    case EXPERT_ORDER_COUNT -> {
                        Subquery<Long> subquery = query.subquery(Long.class);
                        Root<CustomerOrder> subRoot = subquery.from(CustomerOrder.class);

                        Join<CustomerOrder, SubDuty> subDutyJoin = subRoot.join("subService");
                        Join<SubDuty, Expert> expertJoin = subDutyJoin.join("experts");

                        subquery.select(criteriaBuilder.count(subRoot));
                        subquery.where(criteriaBuilder.equal(expertJoin.get("id"), Integer.parseInt(search.getValue())));

                        Expression<Long> ordersCount = subquery.getSelection();

                        predicates.add(criteriaBuilder.equal(criteriaBuilder.literal(1), 1)); // فقط یک شرط نمونه

                        break;
                    }





                    case DOUBLE_JOIN -> {
                         String[] split = search.getJoinTable().split(",");
                         Predicate equal = criteriaBuilder.equal(root.join(split[0]).join(split[1]).get(search.getColumn()), search.getValue());
                         predicates.add(equal);
                        break;
                    }

                    default -> throw new NotFoundException("Unexpected value: " + search.getOperation());
                }

            }
            if (globalOperator.equals(RequestSpecificationDto.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
            } else {
                return criteriaBuilder.or(predicates.toArray(predicates.toArray(new Predicate[0])));

            }

        };
    }
}