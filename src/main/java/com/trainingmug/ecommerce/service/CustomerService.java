package com.trainingmug.ecommerce.service;

import com.trainingmug.ecommerce.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.dto.request.SignUpRequestDto;
import com.trainingmug.ecommerce.dto.response.CustomerResponseDto;
import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto register(SignUpRequestDto signupRequestDto) throws CustomerExistsException;

    Customer getById(int id) throws CustomerNotFoundException;

    Customer update(Customer customer) throws CustomerNotFoundException;

    List<Customer> getAll();


    void delete(int id) throws CustomerNotFoundException;

    CustomerResponseDto login(LoginRequestDto loginRequestDto) throws CustomerNotFoundException;
}