package com.example.microsvc.sb.controller;

import com.example.microsvc.sb.model.Customer;
import com.example.microsvc.sb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("rest")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Customer with id: %d not found", id)
        ));
    }

    @GetMapping(path = "/search/{name}")
    public List<Customer> searchByName(@PathVariable("name") String name) {
        return customerService.findCustomersWithName(name);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        HttpStatus status = customer.getId() > 0 ? HttpStatus.ACCEPTED : HttpStatus.CREATED;
        return new ResponseEntity<>(customerService.saveCustomer(customer), status);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        int cid = customer.getId();
        customerService.getCustomerById(cid).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Invalid customer id: %d", cid)
        ));
        return new ResponseEntity<>(
                customerService.saveCustomer(customer),
                customer.getId() > 0 ? HttpStatus.ACCEPTED : HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeCustomer(@PathVariable("id") int id) {
        Customer customer = customerService.getCustomerById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Customer with id: %d not found", id)
        ));
        customerService.removeCustomer(customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
