package com.indianbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.indianbank.entity.Credential;
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

	@PostMapping("/login")
	public String login(@ModelAttribute("credential") Credential credential, HttpSession session) {
		System.out.println("@PostMapping(\"/login\")");
		User loggedInUser = userRepository.findByIdAndPassword(credential.getUserid(), credential.getUserPassword());
		if (loggedInUser != null) {
			System.out.println(loggedInUser);
			session.setAttribute("userid", loggedInUser.getId());
			session.setAttribute("true", true);
			return "redirect:/index";
		}
		session.setAttribute("true", false);
		return "redirect:/login?error";
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
		session.setAttribute("true", false);
		return "redirect:/login?error";
	}
	
	
	@GetMapping("/index")
	public String dashboard(HttpSession session, Model model) {
		System.out.println("@GetMapping(\"/index\")");
		// Retrieve the user id from the session
		Long userid = (Long) session.getAttribute("userid");
		if (userid != null) {
			User user = userRepository.findById(userid).orElse(null);
			if (user != null) {
				session.setAttribute("user", user);
				session.setAttribute("deladd", user.getDelAdd());
				model.addAttribute("user", user);
				userService.timestamp(userid);
				return "index";
			}
		}
		else {
			return "redirect:/login";
		}
		return "error";
	}

}
