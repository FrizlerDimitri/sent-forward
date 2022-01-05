package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;

import java.util.Optional;

public interface IAccountService {


    Optional <MasterAccount> createMasterAccount(MasterAccount masterAccount);
    Optional <MasterAccount> updateMasterAccount(MasterAccount masterAccount);

    Optional<EmailAccount> createEmailAccount(EmailAccount emailAccount);

    Optional<EmailAccount> loadEmailAccount(EmailAccount  emailAccount);

    Optional<MasterAccount> getMasterAccountByAccountname(String accountName);

    Optional<EmailAccount> getEmailAccountByEmailAddress(String emailAddress);



}
