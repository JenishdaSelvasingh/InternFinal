package com.example.demo.Repo;

import com.example.demo.Entity.UserEntity;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public UserSpecification() {
    }

    public static Specification<UserEntity> searchByColumns(String search, List<String> columns) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();
            if (columns != null && !columns.isEmpty() && search != null && !search.isEmpty()) {
                Iterator var6 = columns.iterator();

                while(var6.hasNext()) {
                    String column = (String)var6.next();
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(column)), "%" + search.toLowerCase() + "%"));
                }
            }

            return criteriaBuilder.or((Predicate[])predicates.toArray(new Predicate[0]));
        };
    }
}
