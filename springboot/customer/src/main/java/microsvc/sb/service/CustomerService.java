package microsvc.sb.service;

import microsvc.sb.model.Customer;
import microsvc.sb.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        logger.debug("Getting all customer from database.");
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        logger.debug("Getting customer by id: {} from database.", id);
        return customerRepository.findById(id);
    }

    public List<Customer> findCustomersWithName(String name) {
        logger.debug("Getting customer with name contains '{}' from database.", name);
        return customerRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }

    public Customer saveCustomer(Customer customer) {
        logger.debug("Saving customer: {} to database.", customer);
        return customerRepository.save(customer);
    }

    public void removeCustomer(Customer customer) {
        logger.debug("Removing customer: {} from database.", customer);
        customerRepository.delete(customer);
    }
}
