package com.oth.sentforward;

import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entity.account.MasterAccount;
import com.oth.sentforward.persistence.entity.userentity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


@RestController
@SpringBootApplication
public class SentForwardApplication  {

	//@Autowired
	//private IMasterAccountRepository repository;

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


		Address address=new Address();
		try {
			accountService.createMasterAccount("Dimitri","Frizler", address,"dFrizler2","123");
		} catch (Exception e) {
			e.printStackTrace();

			return "Account exist already!";
		}
		return "created Master Account ! ";

	}






}
