package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.persistence.entity.account.*;
import com.oth.sentforward.persistence.entity.userentity.Address;
import com.oth.sentforward.persistence.entity.userentity.IUserRepository;
import com.oth.sentforward.persistence.entity.userentity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IMasterAccountRepository masterAccountRepository;

    @Autowired
    private IEmailAccountRepository emailAccountRepository;

    @Autowired
    private IUserRepository userRepository;


    AccountService(IMasterAccountRepository masterAccountRepository, IEmailAccountRepository emailAccountRepository,
                   IUserRepository userRepository) {
        this.masterAccountRepository = masterAccountRepository;
        this.emailAccountRepository = emailAccountRepository;
        this.userRepository = userRepository;
    }

    public Collection<MasterAccount> getAllMasterAccounts() {
        return (Collection<MasterAccount>) masterAccountRepository.findAll();
    }

    public Collection<EmailAccount> getAllEmailAccounts() {
        return (Collection<EmailAccount>) emailAccountRepository.findAll();
    }


    //Todo create a real Exection ???
    //Throw Exception if accountName is not unique
    @Override
    public void createMasterAccount(String userName, String userLastName, Address address,
                                    String accountName, String password
    ) throws Exception {

        UserEntity user = new UserEntity(userName, userLastName, address);
        userRepository.save(user);

        MasterAccount account = new MasterAccount(accountName, password, user);
        masterAccountRepository.save(account);
        user.setMasterAccount(account);

        userRepository.save(user);
    }


    //Throw Exception if emailAddress is not unique
    @Override
    public void createEmailAccount(String emailAddress, String emailPw, String domain,
                                   int port, MasterAccount masterAccount) throws Exception {
        EmailAccount emailAccount = new EmailAccount(emailAddress, emailPw, domain, port, masterAccount);
        emailAccountRepository.save(emailAccount);
        masterAccount.getEmailAccounts().add(emailAccount);
        masterAccountRepository.save(masterAccount);

    }


    @Override
    public boolean logginIntoAccount(String nameOrEmail, String pw) {



        Optional<MasterAccount> master = masterAccountRepository.findByName(nameOrEmail);
        Optional<EmailAccount> emailAccount = emailAccountRepository.findByEmailAddress(nameOrEmail);

        EmailAccount acc = new EmailAccount();

        if (master.isPresent()) {
            MasterAccount account = master.get();
            if (account.getPassword().equals(pw)) {
                return true;
            }

            return false;
        } else if (emailAccount.isPresent()) {
            EmailAccount account = emailAccount.get();
            if (account.getEmailPw().equals(pw)) {
                return true;
            }

            return false;
        } else {

            return false;
        }
    }


}
