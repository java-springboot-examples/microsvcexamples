package microsvc.sb.controller;

import microsvc.sb.model.Account;
import microsvc.sb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("rest")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/{id}")
    public Account getAccountById(@PathVariable("id") String id) {
        return accountService.getAccountById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Account with id: %s not found", id)
        ));
    }

    @GetMapping(path = "/customer/{id}")
    public List<Account> getAccountSByCustomer(@PathVariable("id") int cid) {
        return accountService.getAccountsByCustomer(cid);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Account> addAdcount(@RequestBody Account account) {
        if (accountService.getAccountById(account.getId()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Account with id: %s already exists", account.getId()));
        }
        return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        HttpStatus status = accountService.getAccountById(account.getId()).isPresent() ?
                HttpStatus.ACCEPTED : HttpStatus.CREATED;
        return new ResponseEntity<>(accountService.saveAccount(account), status);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeAccount(@PathVariable("id") String id) {
        Account account = accountService.getAccountById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Account with id: %s not found", id)
        ));
        accountService.removeAccount(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
