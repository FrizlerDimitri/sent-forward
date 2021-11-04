package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.persistence.entity.account.EmailAccount;
import com.oth.sentforward.persistence.entity.account.MasterAccount;
import com.oth.sentforward.persistence.entity.userentity.Address;


import java.util.Collection;

public interface IAccountService {


     Collection<MasterAccount> getAllMasterAccounts();

     Collection<EmailAccount> getAllEmailAccounts();

     void createMasterAccount(String userName, String userLastName,
                                    Address address, String accountName,
                                    String password)
            throws Exception;


     void createEmailAccount(String emailAddress, String emailPw, String domain, int port,
                                   MasterAccount masterAccount)
            throws Exception;

    boolean logginIntoAccount(String nameOrEmail, String pw);


}
