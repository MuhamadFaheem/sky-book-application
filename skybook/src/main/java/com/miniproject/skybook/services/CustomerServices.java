package com.miniproject.skybook.services;

import com.miniproject.skybook.model.dto.response.CustomerSearchDTO;
import com.miniproject.skybook.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerServices {
    Customer saveCustomer(Customer customer);
    Customer getCustomerbyId(String id);
    List<Customer> getAllCustomers(boolean delete);
    Customer updateCustomerbyId(Customer customer);
    String deleteCustomerbyId(String id);

    Page<Customer> getCustomerperPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
}
