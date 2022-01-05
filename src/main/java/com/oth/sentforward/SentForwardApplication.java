package com.oth.sentforward;
import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.bussnislogic.iservices.IEmailService;
import com.oth.sentforward.persistence.entities.*;
import com.oth.sentforward.persistence.repositories.IMasterAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SentForwardApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SentForwardApplication.class, args);
	}


	@Autowired
	IAccountService accountService;

	@Autowired
	ICalendarService calendarService;

	@Autowired
	IEmailService emailService;


	@Override
	public void run(ApplicationArguments args) throws Exception {

		final String masterAccountName = "TestTest";

		MasterAccount testTestMasterAccount;
		EmailAccount  test1EmailAccount;
		EmailAccount  test2EmailAccount;
		EmailAccount  test3EmailAccount;


		Optional<MasterAccount> optionalMasterAccount=  accountService.getMasterAccountByAccountname("TestTest");
		if (optionalMasterAccount.isEmpty())
		{

			MasterAccount masterAccount= new MasterAccount();
			masterAccount.setAccountName("TestTest");
			masterAccount.setPassword("123");
			masterAccount.setUser(new UserEntity("Mustermann", "Max", new Address("Germany", "Musterstraße 3a","Regensburg")));
			optionalMasterAccount=accountService.createMasterAccount(masterAccount);

		}

		testTestMasterAccount=optionalMasterAccount.get();

		final String emailAddress1= "test1@sentforward.de";
		final String emailAddress2= "test2@sentforward.de";
		final String emailAddress3= "test3@sentforward.de";

		Optional<EmailAccount> optionalEmailAccount1 = accountService.getEmailAccountByEmailAddress(emailAddress1);
		Optional<EmailAccount> optionalEmailAccount2 = accountService.getEmailAccountByEmailAddress(emailAddress2);
		Optional<EmailAccount> optionalEmailAccount3 = accountService.getEmailAccountByEmailAddress(emailAddress3);

		if(optionalEmailAccount1.isEmpty())
		{

			EmailAccount e1= new EmailAccount(emailAddress1,"123",testTestMasterAccount);
			testTestMasterAccount.getEmailAccounts().add(e1);
//			ReceivedEmail(String content, String subject, EmailAccount from, Collection<EmailAccount> to, Date receivedDate)
//			ReceivedEmail re=new ReceivedEmail("This is a Email","Your First Email",e1,null,null);

			SentEmail sentEmail= new SentEmail("I send this Email","Test Email",e1,null, new Date());
			SentEmail sentEmail2= new SentEmail("I send this Email 2 ","Test Email 2",e1,null, new Date());
			SentEmail sentEmail3= new SentEmail("I send this Email 3 ","Test Email 3",e1,null, new Date());
			e1.getSentEmails().add(sentEmail);
			e1.getSentEmails().add(sentEmail2);
			e1.getSentEmails().add(sentEmail3);

		}

		if(optionalEmailAccount2.isEmpty())
		{
			testTestMasterAccount.getEmailAccounts().add(new EmailAccount(emailAddress2,"123",testTestMasterAccount));
		}

		if(optionalEmailAccount3.isEmpty())
		{
			testTestMasterAccount.getEmailAccounts().add(new EmailAccount(emailAddress3,"123",testTestMasterAccount));
		}

		accountService.updateMasterAccount(testTestMasterAccount);



	}
}
