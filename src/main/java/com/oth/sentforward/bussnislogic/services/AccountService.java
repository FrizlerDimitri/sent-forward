package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.persistence.entity.account.EmailAccount;
import com.oth.sentforward.persistence.entity.account.ICrudEmailAccountRepository;
import com.oth.sentforward.persistence.entity.account.ICrudMasterAccountRepository;
import com.oth.sentforward.persistence.entity.account.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    @Autowired
    ICrudMasterAccountRepository masterAccountRepository;

    @Autowired
    ICrudEmailAccountRepository emailAccountRepository;

    AccountService(ICrudMasterAccountRepository masterAccountRepository, ICrudEmailAccountRepository emailAccountRepository){
        this.masterAccountRepository=masterAccountRepository;
        this.emailAccountRepository=emailAccountRepository;
    }

    public Collection<MasterAccount> getAllMasterAccounts()
    {
        return (Collection<MasterAccount>)masterAccountRepository.findAll();
    }

    public Collection <EmailAccount> getAllEmailAccounts()
    {
        return (Collection<EmailAccount>)emailAccountRepository.findAll();
    }


}
