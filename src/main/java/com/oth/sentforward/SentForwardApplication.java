package com.oth.sentforward;
import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.bussnislogic.iservices.IEmailService;
import com.oth.sentforward.bussnislogic.services.CalendarService;
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
	CalendarService calendarService;

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

			EmailAccount e1= new EmailAccount(emailAddress1,testTestMasterAccount);
			testTestMasterAccount.getEmailAccounts().add(e1);
//			ReceivedEmail(String content, String subject, EmailAccount from, Collection<EmailAccount> to, Date receivedDate)
//			ReceivedEmail re=new ReceivedEmail("This is a Email","Your First Email",e1,null,null);

			SentEmail sentEmail= new SentEmail("I send this Email dlsjflksdjfljsdj kdjskfjsdkjfj lskdjf lkjsdafj" +
					"sdfsdafasd fasd fdsafasdfasd fdsa fsdaf asd sdafsdaf" +
					"sdf sadfsadfsdaf sdaf" +
					"sdafasd fsdfsdaf sdaf sadf adf asdf " +
					"d fdsaf sadf sdaf sdafsdaf sadf asdf asdf asdf asd fsadf " +
					"sdaf sad fasdfasd fasdf sadf af sad sadf asd fsda fasd f" +
					"asdf asdf sad fasd fasd fasd fsagtasdgfasdfsadfas a" +
					"ddsaf asdf asdf asdf dasf sad dsafasdf sdaf asd" +
					"sd fasdfasdf asdf asdf sdaf sdaf asdf sadf asd fsdf asdf " +
					"asdf sadf asdf sdf sfsad asd sd fdsafasdfasdf sdaf asdf sad f" +
					"sdaf asdf sda fasdf ghafadsastvasdfsda asdfasd ds fdsf asdf asdfsdafasdf asd " +
					"sad asdf assdf asdf sd sdaf d  dfasd.","Test Email",e1,null, new Date());
			SentEmail sentEmail2= new SentEmail("I send this Email 2 ","Test Email 2",e1,null, new Date(System.currentTimeMillis()-2*60000));
			SentEmail sentEmail3= new SentEmail("I send this Email 3 ","Test Email 3",e1,null, new Date(System.currentTimeMillis()-60000));
			e1.getSentEmails().add(sentEmail);
			e1.getSentEmails().add(sentEmail2);
			e1.getSentEmails().add(sentEmail3);


			Appointment today = new Appointment();
			today.setDate(new Date());
			today.setDescription("This is a Test Date for today");
			today.setName("Very important meeting");
			e1.getCalendar().getAppointments().add(today);




		}

		if(optionalEmailAccount2.isEmpty())
		{

			EmailAccount emailAccount2 = new EmailAccount(emailAddress2,testTestMasterAccount);
			testTestMasterAccount.getEmailAccounts().add(emailAccount2);



		}

		if(optionalEmailAccount3.isEmpty())
		{

			testTestMasterAccount.getEmailAccounts().add(new EmailAccount(emailAddress3,testTestMasterAccount));



			ReceivedEmail receivedEmail = new ReceivedEmail();

			receivedEmail.setSubject("Test Subject 1");
			receivedEmail.setContent("This is the content for a Test Email 1 ");
			receivedEmail.setReceivedDate(new Date());

			List<EmailAccount> emailAccounts = (List<EmailAccount>) testTestMasterAccount.getEmailAccounts();

			receivedEmail.setFrom(emailAccounts.get(1));
			receivedEmail.getTo().add(emailAccounts.get(0));
			((List<EmailAccount>) testTestMasterAccount.getEmailAccounts()).get(0).getReceivedEmails().add(receivedEmail);



		}

		accountService.updateMasterAccount(testTestMasterAccount);

//		calendarService.getCooksFromHomeDente();
//		calendarService.orderChefFromHomeDente();


	}
}
