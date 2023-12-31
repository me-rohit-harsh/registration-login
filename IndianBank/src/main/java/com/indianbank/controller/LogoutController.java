package com.indianbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.indianbank.entity.User;
import com.indianbank.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("@GetMapping(\"/logout\")");
		Boolean auth= (Boolean) session.getAttribute("true");
		if(auth!=null&& auth) {
			User user = (User) session.getAttribute("user");
			// Get user's IP address
			String userIpAddress = request.getRemoteAddr();
			user.setIpAdderess(userIpAddress);
			userService.saveUser(user);
			// Invalidate the user's session
			session.invalidate();
			System.out.println("Session invalidated succesfully for user: "+ user);
		}
		return "redirect:/login"; // Redirect to the login page after logout
	}
}
