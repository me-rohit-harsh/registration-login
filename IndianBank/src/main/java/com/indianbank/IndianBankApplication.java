package com.indianbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.indianbank.entity.PasswordReset;
import com.indianbank.repository.PasswordResetRepository;
import com.indianbank.repository.UserRepository;
import com.indianbank.service.PasswordResetService;

@SpringBootApplication
public class IndianBankApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IndianBankApplication.class, args);
		System.out.println("Indian Banking is up and running........");
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordResetRepository passwordResetRepository;
	
	@Override
	public void run(String... args) throws Exception {

//		User user = new User(1L, "Rohit", "Kumar", "Male","rohit@gmail.com", "Saving", "112", 1111.0, "16-10-2002", "Patna, Bihar, India");
//		userRepository.save(user);
//		System.out.println(user);
//		PasswordReset reset= new PasswordReset();
//		reset.setId(10L);
//		reset.setUserPassword("rohit");
//		reset.setEmail("Rohit@Gmail.com");
//		passwordResetRepository.save(reset);
	}

}
