package com.trainingmug.ecommerce.service;

import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer) throws CustomerExistsException;
    Customer getById(int id) throws CustomerNotFoundException;
    Customer update(Customer customer) throws CustomerNotFoundException;
    List<Customer> getAll();
    void delete(int id) throws CustomerNotFoundException;



}
