package com.oth.sentforward;

import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entity.account.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@SpringBootApplication
public class SentForwardApplication  {

	//@Autowired
	//private ICrudMasterAccountRepository repository;

	@Autowired
	private AccountService accountService;


	public static void main(String[] args) {
		SpringApplication.run(SentForwardApplication.class, args);
	}


	@GetMapping("/read")
	public String printMasterAccounts()
	{
		String ret="";
		List<MasterAccount> list=(ArrayList<MasterAccount>) accountService.getAllMasterAccounts();
			for( MasterAccount account :list)
			{
				ret += account.toString()+"\n";
			}


		return ret;
	}


	@GetMapping("/creat")
	public String creat() {

//		for(int i =0; i<100; i++)
//		{
//			MasterAccount m = new MasterAccount("name "+i ,"pw"+i);
//			repository.save(m);
//		}

		return "created 100 Master Accounts ! ";

	}






}
