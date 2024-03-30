package com.miniproject.skybook.utility;


import com.miniproject.skybook.model.dto.response.CustomerSearchDTO;
import com.miniproject.skybook.model.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class CustomerSpesification {
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO){
        return ((root, query, criteriaBuilder) -> {
            //List<Predicate> predicates = new ArrayList<>();
            Predicate predicate= criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("deleted"), false));

            if(customerSearchDTO.getCustomerFirstName() != null){
                Predicate fullname = criteriaBuilder.like(criteriaBuilder
                        .lower(root.get("firstName")),criteriaBuilder.lower(criteriaBuilder.literal("%"+customerSearchDTO.getCustomerFirstName()+"%")));
                predicate =criteriaBuilder.and(predicate,fullname);
            }
            if(customerSearchDTO.getCustomerLastName() != null){
                Predicate getCustomerAddress = criteriaBuilder.like
                        (criteriaBuilder.lower
                                        (root.get("lastName")),
                                criteriaBuilder.lower(
                                        criteriaBuilder.literal(
                                                "%"+customerSearchDTO.getCustomerLastName()+"%")));
                predicate = criteriaBuilder.and(predicate,getCustomerAddress);
            }
            if(customerSearchDTO.getCustomerDateOfBirth() != null){
                String dob = customerSearchDTO.getCustomerDateOfBirth().toString();
                String jam =dob.substring(11,19);
                System.out.println(jam.replace('0','7'));
                System.out.println();
                Predicate custDOB = criteriaBuilder.equal(root.get("date_of_birth"),customerSearchDTO.getCustomerDateOfBirth());
                predicate = criteriaBuilder.and(predicate,custDOB);
            }
            return predicate;
        });
    }
}