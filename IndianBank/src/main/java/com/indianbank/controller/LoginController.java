package com.indianbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.indianbank.entity.PasswordReset;
import com.indianbank.entity.User;
import com.indianbank.repository.PasswordResetRepository;
import com.indianbank.repository.UserRepository;
import com.indianbank.service.PasswordResetService;
import com.indianbank.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private PasswordResetService passwordResetService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetRepository passwordResetRepository;
	@Autowired
	private UserRepository userRepository;

	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}

	public LoginController() {
		super();
	}

	public LoginController(PasswordResetService passwordResetService) {
		super();
		this.passwordResetService = passwordResetService;
	}

	@GetMapping("/login")
	public String loginForm() {
		System.out.println("@GetMapping(\"login\")");
		return "login";
	}

	@PostMapping("/reset")
	public String viewPassword(@ModelAttribute("PasswordReset") PasswordReset passwordReset, HttpSession session) {
	    System.out.println("@PostMapping(\"/reset\")");

	    passwordResetService.saveUser(passwordReset);
	    
	    User mainUser = userRepository.findById(passwordReset.getUserId()).orElse(null);
	    
	    if (mainUser != null && mainUser.getEmail().equals(passwordReset.getEmail())) {
	        session.setAttribute("userPassword", mainUser.getPassword());
	        session.setAttribute("id", passwordReset.getUserId());
	        return "redirect:/login?success";
	    }

	    return "redirect:/login?error";
	}

}
