package com.geekbrains.repositories.specifications;

import com.geekbrains.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {
    public static Specification<Product> costGreaterOrEq(double value){
        return (Specification<Product>)(root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
        };
    }

    public static Specification<Product> costLesserOrEq(double value){
        return (Specification<Product>)(root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), value);
        };
    }
}
