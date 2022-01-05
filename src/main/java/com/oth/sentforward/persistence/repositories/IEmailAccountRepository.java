package com.oth.sentforward.persistence.repositories;


import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmailAccountRepository extends JpaRepository<EmailAccount,Long> {

    Optional<EmailAccount> findEmailAccountById(Long id);

    Optional<EmailAccount> findEmailAccountByEmailAddress(String emailAddress);


}
