package com.oth.sentforward.persistence.entity.account;


import com.oth.sentforward.persistence.entity.userentity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMasterAccountRepository extends IAccountRepository<MasterAccount, Long > {

    Optional<MasterAccount> findByName(String name);
    Optional<MasterAccount> findByUser(UserEntity user);

}
