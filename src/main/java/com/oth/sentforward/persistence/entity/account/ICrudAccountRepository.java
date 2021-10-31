package com.oth.sentforward.persistence.entity.account;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface ICrudAccountRepository<T,ID> extends CrudRepository<T,ID> {

}
