package com.oth.sentforwardpersistence;

import com.oth.sentforwardpersistence.Account.Account;
import com.oth.sentforwardpersistence.Account.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentForwardPersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentForwardPersistenceApplication.class, args);


		AccountService service=new AccountService();
		for ( Account acc : service.findAll())
		{
			System.out.println(acc.toString());
		}



	}

}
