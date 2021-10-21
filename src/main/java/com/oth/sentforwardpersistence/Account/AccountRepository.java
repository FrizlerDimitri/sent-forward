package com.oth.sentforwardpersistence.Account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findAccountByAccountName(String accountName);
}
