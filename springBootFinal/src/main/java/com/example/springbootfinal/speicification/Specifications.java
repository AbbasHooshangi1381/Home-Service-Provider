package com.example.springbootfinal.speicification;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import jakarta.persistence.criteria.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
public class Specifications {
    public static Specification<CustomerOrder> orderByCriteria(Map<String, Object> criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.containsKey("startDate") && criteria.containsKey("endDate")) {
                LocalDateTime startDate = (LocalDateTime) criteria.get("startDate");
                LocalDateTime endDate = (LocalDateTime) criteria.get("endDate");
                predicates.add(criteriaBuilder.between(root.get("timeOfDoing"), startDate, endDate));
            }
            if (criteria.containsKey("status")) {
                StatusOfOrder status = StatusOfOrder.valueOf((String) criteria.get("status"));
                predicates.add(criteriaBuilder.equal(root.get("statusOfOrder"), status));
            }
            if (criteria.containsKey("subDuty")) {
                Integer subDutyId = (Integer) criteria.get("subDuty");
                predicates.add(criteriaBuilder.equal(root.get("subService").get("id"), subDutyId));
            }
            if (criteria.containsKey("expertIdWithDone")) {
                Integer expertId = (Integer) criteria.get("expertIdWithDone");

                predicates.add(criteriaBuilder.equal(root.get("statusOfOrder"), StatusOfOrder.DONE));

                Join<CustomerOrder, SubDuty> subDutyJoin = root.join("subService", JoinType.INNER);
                Join<SubDuty, BaseUser> expertJoin = subDutyJoin.join("expert", JoinType.INNER);

                predicates.add(criteriaBuilder.equal(expertJoin.get("id"), expertId));
            }

            if (criteria.containsKey("customerIdWithDone")) {
                Integer customerId = (Integer) criteria.get("customerIdWithDone");

                predicates.add(criteriaBuilder.equal(root.get("statusOfOrder"), StatusOfOrder.DONE));

                Join<CustomerOrder, BaseUser> customerJoin = root.join("customer", JoinType.INNER);

                predicates.add(criteriaBuilder.equal(customerJoin.get("id"), customerId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
        public static Specification<BaseUser> reportCustomerExpert(Map<String, Object> criteria) {
            return (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (criteria.containsKey("userId")) {
                    Integer userId = (Integer) criteria.get("userId");
                    predicates.add(cb.equal(root.get("id"), userId));
                }

                if (criteria.containsKey("dateOfSigningInAfter")) {
                    LocalDate signingInAfter = (LocalDate) criteria.get("dateOfSigningInAfter");
                    predicates.add(cb.greaterThanOrEqualTo(root.get("dateOfSigningIn"), signingInAfter));
                }

                if (criteria.containsKey("minOrdersCount")) {
                    Integer minOrdersCount = (Integer) criteria.get("minOrdersCount");

                    Subquery<Long> orderSubquery = query.subquery(Long.class);
                    Root<CustomerOrder> orderRoot = orderSubquery.from(CustomerOrder.class);
                    orderSubquery.select(cb.count(orderRoot));
                    orderSubquery.where(cb.equal(orderRoot.get("customer"), root), cb.isNotNull(orderRoot.get("id")));

                    predicates.add(cb.greaterThanOrEqualTo(orderSubquery, minOrdersCount.longValue()));
                }

                if (criteria.containsKey("minSuggestionsCount")) {
                    Integer minSuggestionsCount = (Integer) criteria.get("minSuggestionsCount");

                    Subquery<Long> suggestionSubquery = query.subquery(Long.class);
                    Root<Suggestion> suggestionRoot = suggestionSubquery.from(Suggestion.class);
                    suggestionSubquery.select(cb.count(suggestionRoot));
                    suggestionSubquery.where(cb.equal(suggestionRoot.get("expert"), root), cb.isNotNull(suggestionRoot.get("id")));

                    predicates.add(cb.greaterThanOrEqualTo(suggestionSubquery, minSuggestionsCount.longValue()));
                }

                query.distinct(true);

                return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
            };
        }
    }



