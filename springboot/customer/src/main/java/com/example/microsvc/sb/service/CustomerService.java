package com.example.microsvc.sb.service;

import com.example.microsvc.sb.model.Customer;
import com.example.microsvc.sb.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findCustomersWithName(String name) {
        return customerRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void removeCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
