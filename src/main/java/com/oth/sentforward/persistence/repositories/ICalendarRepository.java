package com.oth.sentforward.persistence.repositories;

import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICalendarRepository extends JpaRepository<Calendar, Long> {
}
