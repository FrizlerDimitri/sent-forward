package com.oth.sentforward.persistence.entity.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICrudEmailAccountRepository extends ICrudAccountRepository< EmailAccount,Long> {

}
