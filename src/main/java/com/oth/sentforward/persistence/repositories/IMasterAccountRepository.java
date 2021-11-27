package com.oth.sentforward.persistence.repositories;


import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMasterAccountRepository extends JpaRepository<MasterAccount, Long> {



    Optional<MasterAccount> findMasterAccountByAccountName(String accountName);

}
