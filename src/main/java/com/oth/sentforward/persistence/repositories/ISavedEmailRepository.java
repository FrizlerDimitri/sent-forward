package com.oth.sentforward.persistence.repositories;

import com.oth.sentforward.persistence.entities.SavedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISavedEmailRepository extends JpaRepository<SavedEmail, Long> {

    @Override
    Optional<SavedEmail> findById(Long id);

}