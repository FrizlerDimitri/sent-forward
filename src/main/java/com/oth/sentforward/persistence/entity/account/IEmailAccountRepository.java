package com.oth.sentforward.persistence.entity.account;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmailAccountRepository extends IAccountRepository< EmailAccount,Long> {



    Optional<EmailAccount> findByEmailAddress(String emailAddress);
}
