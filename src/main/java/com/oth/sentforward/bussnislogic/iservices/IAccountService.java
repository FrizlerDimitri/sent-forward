package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.UserEntity;

import java.util.Optional;

public interface IAccountService {


    Optional <MasterAccount> createMasterAccount(MasterAccount masterAccount);


}
