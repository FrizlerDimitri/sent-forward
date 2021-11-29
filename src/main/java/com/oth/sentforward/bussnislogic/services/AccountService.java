package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.UserEntity;
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
import java.util.function.Consumer;

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


    private Optional<MasterAccount> loadMasterAccountUserByUsername(String accountName) {
        return masterAccountRepository.findMasterAccountByAccountName(accountName);
    }

    //TODO same for EmailAccount

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MasterAccount> optionalMasterAccount = loadMasterAccountUserByUsername(username);

        optionalMasterAccount.orElseThrow(() -> {
            throw new UsernameNotFoundException("User with username = " + username +" not found");
        });

        return optionalMasterAccount.get();
    }
}
