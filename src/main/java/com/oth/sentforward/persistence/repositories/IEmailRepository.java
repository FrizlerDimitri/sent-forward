package com.oth.sentforward.persistence.repositories;

import com.oth.sentforward.persistence.entities.AbstractEmail;
import com.oth.sentforward.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmailRepository extends JpaRepository<AbstractEmail, Long> {
}
