package com.example.microsvc.sb.repo;

import com.example.microsvc.sb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
