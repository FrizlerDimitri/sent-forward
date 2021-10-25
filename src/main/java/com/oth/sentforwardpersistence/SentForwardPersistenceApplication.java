package com.oth.sentforwardpersistence;

import com.oth.sentforwardpersistence.Account.Account;
import com.oth.sentforwardpersistence.Account.AccountRepository;
import com.oth.sentforwardpersistence.Account.AccountService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class SentForwardPersistenceApplication {


	@Autowired
	private AccountRepository repo;


	public static void main(String[] args) {
		SpringApplication.run(SentForwardPersistenceApplication.class, args);
	}



	@GetMapping("/hello")


	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {


		for(long i =0l;i<100; i++ )
		{
			Account account= new Account(i,"name"+i,"123"+i);
			repo.save(account);
		}


		String s ="";

		for ( Account account : repo.findAll())
		{
			s+=account.toString()+"ende \n" ;

		}


		return s;




	}






}
