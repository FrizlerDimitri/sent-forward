package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.UserEntity;
import com.oth.sentforward.persistence.repositories.IEmailAccountRepository;
import com.oth.sentforward.persistence.repositories.IMasterAccountRepository;
import com.oth.sentforward.persistence.repositories.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IMasterAccountRepository masterAccountRepository;

    @Autowired
    private IEmailAccountRepository emailAccountRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    public AccountService(IMasterAccountRepository masterAccountRepository, IEmailAccountRepository emailAccountRepository, IUserEntityRepository userEntityRepository) {
        this.masterAccountRepository = masterAccountRepository;
        this.emailAccountRepository = emailAccountRepository;
        this.userEntityRepository = userEntityRepository;
    }

    //TODO Exceptions
    @Override
    public Optional<MasterAccount> createMasterAccount(MasterAccount masterAccount) {

        System.out.println("In create Master Account !");

        if (isMasterAccountNameInUse(masterAccount)) {
            return Optional.empty();
        }


        Optional<MasterAccount> optionalMasterAccount = Optional.of(masterAccountRepository.save(masterAccount));

//        UserEntity user = masterAccount.getUser();
//        userEntityRepository.saveAndFlush(user);
//        user.setMasterAccount(masterAccount);
//        Optional<MasterAccount> optionalMasterAccount = Optional.of(masterAccountRepository.saveAndFlush(masterAccount));
//        userEntityRepository.saveAndFlush(masterAccount.getUser());

        return optionalMasterAccount;
    }


    private boolean isMasterAccountNameInUse(MasterAccount masterAccount) {

        String accountName = masterAccount.getAccountName();

        Optional<MasterAccount> optionalMasterAccount = masterAccountRepository.findMasterAccountByAccountName(accountName);
        masterAccountRepository.flush();
        //optionalMasterAccount.ifPresent(acc -> System.out.println(acc));

        optionalMasterAccount.ifPresent(acc -> System.out.println(acc));

        if (optionalMasterAccount.isEmpty()) {

            return false;
        }

        return true;
    }


}
