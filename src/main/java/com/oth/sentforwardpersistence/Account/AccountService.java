package com.oth.sentforwardpersistence.Account;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;


public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public Account findAccountByID(long accountID)
    {
        Optional<Account> optionalAccount = accountRepo.findById(accountID);
        return optionalAccount.orElse(null);
    }

    public List<Account> findAll()
    {
        return (List<Account>) accountRepo.findAll();
    }

    public void createAccount(Account account)
    {
        accountRepo.save(account);
    }

}
