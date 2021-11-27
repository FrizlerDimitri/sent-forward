package com.oth.sentforward.persistence.repositories;


import com.oth.sentforward.persistence.entities.EmailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmailAccountRepository extends JpaRepository<EmailAccount,Long> {
}
