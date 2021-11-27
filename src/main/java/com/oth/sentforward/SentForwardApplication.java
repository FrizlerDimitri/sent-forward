package com.oth.sentforward;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


//@RestController
@SpringBootApplication
public class SentForwardApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SentForwardApplication.class, args);
	}


}
