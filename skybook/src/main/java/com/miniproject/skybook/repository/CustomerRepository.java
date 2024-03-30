package com.miniproject.skybook.repository;

import com.miniproject.skybook.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, String>, JpaSpecificationExecutor<Customer> {

    List<Customer> findCustomerByFirstNameContainsIgnoreCase(String firstName);
    Page<Customer> findAllByStatusIsFalse(Pageable pageable);
}