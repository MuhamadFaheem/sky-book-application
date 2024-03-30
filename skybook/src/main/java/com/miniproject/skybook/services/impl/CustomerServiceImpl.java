package com.miniproject.skybook.services.impl;

import com.miniproject.skybook.model.dto.response.CustomerSearchDTO;
import com.miniproject.skybook.model.entity.Customer;
import com.miniproject.skybook.repository.CustomerRepository;
import com.miniproject.skybook.services.CustomerServices;
import com.miniproject.skybook.utility.CustomerSpesification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerServices {
    private final CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerbyId(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAllCustomers(boolean delete) {
        return null;
    }

    @Override
    public Customer updateCustomerbyId(Customer customer) {
        if(customerRepository.findById(customer.getId()).isPresent()){
            return customerRepository.save(customer);
        }else{
            throw new RuntimeException("Customer with id: "+customer.getId()+" not found!");
        }
    }

    @Override
    public String deleteCustomerbyId(String id) {
        if(customerRepository.existsById(id)){
            customerRepository.delete(customerRepository.getReferenceById(id));
            return "Customer with id("+id+") has been successfully deleted";
        }else{
            throw new RuntimeException("Customer with id: "+id+" not found!");
        }
    }
    @Override
    public Page<Customer> getCustomerperPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> specification = CustomerSpesification.getSpecification(customerSearchDTO);
        return customerRepository.findAll(specification,pageable);
    }
}