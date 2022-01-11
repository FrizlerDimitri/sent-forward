package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.repositories.IEmailAccountRepository;
import com.oth.sentforward.persistence.repositories.IMasterAccountRepository;
import com.oth.sentforward.persistence.repositories.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        masterAccount.setPassword(passwordEncoder.encode(masterAccount.getPassword()));
        Optional<MasterAccount> optionalMasterAccount = Optional.of(masterAccountRepository.save(masterAccount));
        return optionalMasterAccount;
    }


    @Override
    public Optional<MasterAccount> updateMasterAccount(MasterAccount masterAccount) {
        Optional<MasterAccount> optionalMasterAccount = Optional.of(masterAccountRepository.save(masterAccount));
        return optionalMasterAccount;
    }

    @Override
    public Optional<EmailAccount> updateEmailAccount(EmailAccount emailAccount) {
        return Optional.of(emailAccountRepository.save(emailAccount));
    }

    @Override
    public Optional<Iterable<EmailAccount>> updateAllEmailAccount(Iterable<EmailAccount> emailAccounts) {
        return Optional.of(emailAccountRepository.saveAll(emailAccounts));
    }

    @Override
    public Optional<EmailAccount> createEmailAccount(EmailAccount emailAccount) {

        Optional<EmailAccount> optionalMasterAccount = Optional.of(emailAccountRepository.save(emailAccount));

        return optionalMasterAccount;
    }

    @Override
    public Optional<EmailAccount> loadEmailAccount(EmailAccount emailAccount) {

        return emailAccountRepository.findEmailAccountById(emailAccount.getId());
    }

    @Override
    public Optional<MasterAccount> getMasterAccountByAccountname(String accountName) {

        return masterAccountRepository.findMasterAccountByAccountName(accountName);
    }

    @Override
    public Optional<EmailAccount> getEmailAccountByEmailAddress(String emailAddress) {
        return emailAccountRepository.findEmailAccountByEmailAddress(emailAddress);
    }

    @Override
    public Optional<EmailAccount> getEmailAccountById(Long id) {
        return emailAccountRepository.findEmailAccountById(id);
    }


    public Optional<MasterAccount> loadMasterAccountUserByUsername(String accountName) {
        return masterAccountRepository.findMasterAccountByAccountName(accountName);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MasterAccount> optionalMasterAccount = loadMasterAccountUserByUsername(username);

        optionalMasterAccount.orElseThrow(() -> {
            throw new UsernameNotFoundException("user with username = " + username + " not found");
        });
        return optionalMasterAccount.get();
    }
}
