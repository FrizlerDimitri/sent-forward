package com.oth.sentforward.persistence.repositories;

import com.oth.sentforward.persistence.entities.ReceivedEmail;
import com.oth.sentforward.persistence.entities.SentEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReceivedEmailRepository extends JpaRepository<ReceivedEmail, Long> {

    @Override
    Optional<ReceivedEmail> findById(Long id);

}
