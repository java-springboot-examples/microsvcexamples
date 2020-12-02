package com.example.microsvc.sb.service;

import com.example.microsvc.sb.ServiceConfig;
import com.example.microsvc.sb.ServiceConfigurations;
import com.example.microsvc.sb.dto.Balance;
import com.example.microsvc.sb.model.Account;
import com.example.microsvc.sb.model.Transaction;
import com.example.microsvc.sb.repo.TransactionRepository;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private static final String ACCOUNT_SERVICE_NAME = "account";

    @Autowired
    private ServiceConfigurations serviceConfigs;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    private String accountServiceUrl;

    @PostConstruct
    private void setAccountServiceUrl() {
        ServiceConfig serviceConfig = serviceConfigs.get(ACCOUNT_SERVICE_NAME);
        if (serviceConfig == null) {
            throw new RuntimeException(String.format("No config defined for service: '%s'", ACCOUNT_SERVICE_NAME));
        }
        accountServiceUrl = serviceConfig.getUrl();
        logger.debug("accountServiceUrl is configured to {}", accountServiceUrl);
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    private Account getAccount(String id) {
        try {
            ResponseEntity<Account> response = restTemplate.getForEntity(
                    accountServiceUrl + "/rest/" + id, Account.class);
            Account account = response.getBody();
            logger.debug("Get account: {} by id: {} from account service: {}", account, id, accountServiceUrl);
            return account;
        } catch (HttpStatusCodeException ex) {
            if (ex.getRawStatusCode() == 404) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Account with id: %s not found", id));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        }
    }

    public List<Transaction> getTransactionsByAccount(String accountId) {
        Account account = getAccount(accountId);
        return transactionRepository.findByAccount(account.getId());
    }

    public List<Transaction> getTransactionsByAccount(String accountId, LocalDateTime from, LocalDateTime to) {
        Account account = getAccount(accountId);
        return transactionRepository.findByAccountAndTimeBetween(account.getId(), from, to);
    }

    public Transaction addTransaction(Transaction transaction) {
        if (transaction.getTime() == null) {
            transaction.setTime(LocalDateTime.now());
        }
        return transactionRepository.save(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public Balance getAccountBalance(String accountId, LocalDateTime time) {
        Account account = getAccount(accountId);
        float amount = transactionRepository.sumTransactionAmount(accountId, time);
        Balance balance = new Balance();
        balance.setAmount(amount);
        balance.setCurrency(account.getCurrency());
        balance.setTime(time);
        return balance;
    }

}
