package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.dto.request.SignUpRequestDto;
import com.trainingmug.ecommerce.dto.response.CustomerResponseDto;
import com.trainingmug.ecommerce.enums.Status;
import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.entity.Customer;
import com.trainingmug.ecommerce.repository.CustomerRepository;
import com.trainingmug.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    @Override
    public CustomerResponseDto register(SignUpRequestDto signupRequestDto) throws CustomerExistsException {
        customerRepository.findByEmail(signupRequestDto.getEmail()).ifPresent(c -> {
            throw new CustomerExistsException("Customer exists with email: " + signupRequestDto.getName());
        });

//        Customer customer = Customer.builder()
//                .name(signupRequestDto.getName())
//                .email(signupRequestDto.getEmail())
//                .password(signupRequestDto.getPassword())
//                .phone(signupRequestDto.getPhone())
//                .gender(signupRequestDto.getGender())
//                .build();
        Customer customer = modelMapper.map(signupRequestDto, Customer.class);
        //Customer Entity
        customer.setStatus(Status.ACTIVE);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastLoggedInAt(LocalDateTime.now());
        /*Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponseDto.builder()
                .id(savedCustomer.getId())
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .status(savedCustomer.getStatus())
                .build();*/
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public Customer getById(int id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer update(Customer customer) throws CustomerNotFoundException {
        customerRepository.findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customer.getId()));
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(int id) throws CustomerNotFoundException {
        customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponseDto login(LoginRequestDto loginRequestDto) throws CustomerNotFoundException {

        /*Customer customer = customerRepository.findDistinctByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + loginRequestDto.getEmail()));

        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .status(customer.getStatus())
                .build();*/
        return modelMapper.map(customerRepository.findDistinctByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + loginRequestDto.getEmail())), CustomerResponseDto.class);

    }
}