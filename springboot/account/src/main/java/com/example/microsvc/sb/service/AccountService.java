package com.example.microsvc.sb.service;

import com.example.microsvc.sb.ServiceConfig;
import com.example.microsvc.sb.ServiceConfigurations;
import com.example.microsvc.sb.model.Account;
import com.example.microsvc.sb.model.Customer;
import com.example.microsvc.sb.repo.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private static final String CUSTOMER_SERVICE_NAME = "customer";

    @Autowired
    private ServiceConfigurations serviceConfigs;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

    private String customerServiceUrl;

    @PostConstruct
    private void setCustomerServiceUrl() {
        ServiceConfig serviceConfig = serviceConfigs.get(CUSTOMER_SERVICE_NAME);
        if (serviceConfig == null) {
            throw new RuntimeException(String.format("No config defined for service: '%s'", CUSTOMER_SERVICE_NAME));
        }
        customerServiceUrl = serviceConfig.getUrl();
        logger.debug("customerServiceUrl is configured to {}", customerServiceUrl);
    }

    public Optional<Account> getAccountById(String id) {
        logger.debug("Getting account by id: {} from database.", id);
        return accountRepository.findById(id);
    }

    private Customer getCustomer(int id) {
        try {
            ResponseEntity<Customer> response = restTemplate.getForEntity(
                    customerServiceUrl + "/rest/" + id, Customer.class);
            Customer customer = response.getBody();
            logger.debug("Get customer: {} by id: {} from customer service: {}", customer, id, customerServiceUrl);
            return customer;
        } catch (HttpStatusCodeException ex) {
            if (ex.getRawStatusCode() == 404) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Customer with id: %s not found", id));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        }
    }

    public List<Account> getAccountsByCustomer(int cid) {
        logger.debug("Getting accounts for customer by id: {} from database.", cid);
        Customer customer = getCustomer(cid);
        return accountRepository.findByCustomer(customer.getId());
    }

    public Account saveAccount(Account account) {
        logger.debug("Saving account: {} to database.", account);
        return accountRepository.save(account);
    }

    public void removeAccount(Account account) {
        logger.debug("Removing account: {} from database.", account);
        accountRepository.delete(account);
    }
}
