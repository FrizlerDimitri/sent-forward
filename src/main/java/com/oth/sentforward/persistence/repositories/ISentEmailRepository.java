package com.oth.sentforward.persistence.repositories;

import com.oth.sentforward.persistence.entities.AbstractEmail;
import com.oth.sentforward.persistence.entities.SentEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISentEmailRepository extends JpaRepository<SentEmail, Long> {

    @Override
    Optional<SentEmail> findById(Long id);

}
