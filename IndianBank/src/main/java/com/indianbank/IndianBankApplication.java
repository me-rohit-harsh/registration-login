package com.indianbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.indianbank.repository.DelAddRepository;
import com.indianbank.repository.PasswordResetRepository;
import com.indianbank.repository.UserRepository;
import com.indianbank.service.UserService;

@SpringBootApplication
public class IndianBankApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IndianBankApplication.class, args);
		System.out.println("Indian Banking is up and running........");
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetRepository passwordResetRepository;

	@Autowired
	private DelAddRepository delAddRepository;


	@Override
	public void run(String... args) throws Exception {
//		DelAdd deladd = new DelAdd();
//		deladd.setId(1L);
//		deladd.setPincode(201301L);
//		deladd.setState("Bihar");
//		deladd.setStreet("Sandalpur more");
//		delAddRepository.save(deladd);
//		System.out.println(deladd);
//		User user = new User();
//		System.out.println("Created new user" + user.getId());
//		FileInputStream fis = new FileInputStream("src/main/java/profile.jpg");
//		byte[] data = new byte[fis.available()];
//		fis.read(data);
//		user.setImage(data);
//		userService.saveUser(user);
//		userRepository.save(user);
//		System.out.println(user);
//		System.out.println("Saved");
//		PasswordReset reset = new PasswordReset();
//		reset.setId(10L);
//		reset.setUserPassword("rohit");
//		reset.setEmail("Rohit@Gmail.com");
//		passwordResetRepository.save(reset);

	}

}
