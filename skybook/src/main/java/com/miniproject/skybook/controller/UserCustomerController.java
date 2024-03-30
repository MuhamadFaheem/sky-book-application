package com.miniproject.skybook.controller;

import com.miniproject.skybook.constant.ConstantAPIPath;
import com.miniproject.skybook.constant.Messages;
import com.miniproject.skybook.model.dto.response.CustomerSearchDTO;
import com.miniproject.skybook.model.dto.response.PageResponseWrapper;
import com.miniproject.skybook.model.dto.response.ResponseHandler;
import com.miniproject.skybook.model.entity.Customer;
import com.miniproject.skybook.services.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(ConstantAPIPath.API+ConstantAPIPath.CUSTOMER)
public class UserCustomerController {
    private final CustomerServices customerServices;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllCustomers(@RequestParam(name = "page",defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(name = "size",defaultValue = "5") Integer pageSize,
                                                  @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
                                                  @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir,
                                                  @RequestParam(name = "firstName", required = false) String firstName,
                                                  @RequestParam(name = "lastName", required = false) String lastName,
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth
    ){
        CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO();
        customerSearchDTO.setCustomerFirstName(firstName);
        customerSearchDTO.setCustomerLastName(lastName);
        customerSearchDTO.setCustomerDateOfBirth(dateOfBirth);
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir),sortBy);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Customer> customerPage = customerServices.getCustomerperPage(pageable, customerSearchDTO);
        System.out.println(customerPage);
        PageResponseWrapper result = new PageResponseWrapper<>(customerPage);
        return ResponseHandler.generateResponse(Messages.FETCHED, HttpStatus.ACCEPTED, result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getCustomerbyId(@PathVariable String id){
        try {
            Customer result = customerServices.getCustomerbyId(id);
            return ResponseHandler.generateResponse(Messages.RETRIEVED, HttpStatus.ACCEPTED, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCustomerbyId(@PathVariable String id){
        try {
            customerServices.deleteCustomerbyId(id);
            return ResponseHandler.generateResponse(Messages.DELETED, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateCustomerbyId(@RequestBody Customer customer){
        try {
            customerServices.updateCustomerbyId(customer);
            Customer res = customerServices.getCustomerbyId(customer.getId());
            if(res.getId().equalsIgnoreCase(customer.getId())){
                Customer result = customerServices.updateCustomerbyId(customer);
                return ResponseHandler.generateResponse(Messages.UPDATED, HttpStatus.ACCEPTED, result);
            }else{
                return ResponseHandler.generateResponse(Messages.FAILEDUPDATED, HttpStatus.BAD_REQUEST, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }
}
