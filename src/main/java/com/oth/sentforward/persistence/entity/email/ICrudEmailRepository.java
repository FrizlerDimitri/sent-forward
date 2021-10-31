package com.oth.sentforward.persistence.entity.email;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICrudEmailRepository extends CrudRepository<AbstractEmail, Long> {

}
