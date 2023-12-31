package com.indianbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.indianbank.entity.User;
import com.indianbank.repository.UserRepository;
import com.indianbank.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/users")
	public String allUsers(@RequestParam(name = "searchKey", defaultValue = "") String searchKey,
			@RequestParam(name = "genderFilter", defaultValue = " ") Character genderFilter, Model model,
			HttpSession session) {

		System.out.println("@GetMapping(\"/users\")");
		Boolean auth = (Boolean) session.getAttribute("true");
		User user = (User) session.getAttribute("user");
		model.addAttribute(user);
		System.out.println(auth);
		if (auth != null && auth) {
			if (searchKey.isEmpty() && genderFilter.equals(' ')) {
				model.addAttribute("users", userService.allUser());
				return "users";
			} else {
				session.setAttribute("trueMsg", true);
				List<User> users;
				if (genderFilter.equals(' ')) {
					// If no gender filter is selected, use only the search key.

					users = userRepository.findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(searchKey,
							searchKey);
				} else {
					// If a gender filter is selected, use both search key and gender criteria.

					users = userRepository.findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCaseAndSex(searchKey,
							searchKey, genderFilter);
				}
				model.addAttribute("users", users);
				return "users";
			}
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/searchUser")
	public String searchUser() {
		System.out.println("@GetMapping(\"/searchUser\")");
		return "redirect:/users";
	}

	@GetMapping("/register")
	public String createUserForm(Model model) {
		System.out.println("@GetMapping(\"register\")");
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping("/user")
	public String saveUser(@ModelAttribute("user") User user, HttpSession session) {
		Long userId = userService.saveUserId(user);
		session.setAttribute("userId", userId);
		return "redirect:/register?success";
	}

//In This using HttpSession to pass the attribute not Model because of its scope

//	@GetMapping("/index")
//	public String dashboard(Model model) {
//		System.out.println("@GetMapping(\"/index\")");
//		model.addAttribute("users", userService.allUser());
//		return "index";
//	}
	@GetMapping("/")
	public String landing() {
		return "landing";
	}
}
