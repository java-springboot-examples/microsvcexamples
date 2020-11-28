package com.example.microsvc.sb.service;

import com.example.microsvc.sb.model.Account;
import com.example.microsvc.sb.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAccountsByCustomer(int cid) {
        return accountRepository.findByCustomer(cid);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void removeAccount(Account account) {
        accountRepository.delete(account);
    }
}
