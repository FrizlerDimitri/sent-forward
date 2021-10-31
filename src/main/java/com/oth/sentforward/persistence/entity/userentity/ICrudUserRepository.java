package com.oth.sentforward.persistence.entity.userentity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICrudUserRepository extends CrudRepository<UserEntity, Long> {

}
