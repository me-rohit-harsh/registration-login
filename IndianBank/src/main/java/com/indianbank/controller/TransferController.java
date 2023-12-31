package com.indianbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.indianbank.entity.Transactions;
import com.indianbank.entity.User;
import com.indianbank.repository.UserRepository;
import com.indianbank.service.TransactionService;
import com.indianbank.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransferController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionService transactionService;

	@GetMapping("/transfer")
	public String transacion(HttpSession session) {
		System.out.println("@GetMapping(\"/transfer\")");
//		User user = (User) session.getAttribute("user");

//		here what i am doing is that first checking that user is logged in or not
//		then checking that it passes the authentication part
//		It will prevent us from direct illegal access of the end points
		Boolean auth = (Boolean) session.getAttribute("true");
		if (auth != null) {
			if ((Boolean) session.getAttribute("transferMsg") != null) {
				return "transfer";
			} else {
				return "redirect:/index";
			}
		}

		return "redirect:/login";

	}

	@PostMapping("/transfer")
	public String auth(@ModelAttribute("user") User user, HttpSession session, Model model) {

		System.out.println("@PostMapping(\"/transfer\")");
		User newUser = (User) session.getAttribute("user");
		if (newUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("transferMsg", true);
			model.addAttribute("user", newUser);
			return "transfer";
		}
		session.setAttribute("denied", "Oops!! You have entred incorrect password");
		session.setAttribute("msg", false);
		return "redirect:/index?denied";
	}

	@PostMapping("/findUserbyId")
	public String findUserById(@RequestParam("userId") Long userId, Model model, HttpSession session) {
		System.out.println("@PostMapping(\"/findUserbyId\")");

		User benificiaryUser = userRepository.findById(userId).orElse(null);

		if (benificiaryUser != null) {
			session.setAttribute("benificiaryUser", benificiaryUser);
			model.addAttribute("beneficiaryName", benificiaryUser.getFname() + " " + benificiaryUser.getLname());
			model.addAttribute("benificiaryId", userId);
			session.setAttribute("msg", false);
			return "transfer";
		} else {
			session.setAttribute("msg", false);
			session.setAttribute("notFoundMsg", "Oops!! User not found");
			return "redirect:/index?notFound";
		}
	}

	/*
	 * trnsactionAmmount transactionType initialBalance finalBalance transactionId
	 * userId
	 */
	@PostMapping("sender")
	public String sender(@RequestParam("balance") double balance, HttpSession session) {
		System.out.println("@PostMapping(\"sender\")");
		User user = (User) session.getAttribute("user");
		if (user.getBalance() >= balance) {
			User benificiaryUser = (User) session.getAttribute("benificiaryUser");
			Transactions transactions = new Transactions(balance, "Debit", user.getBalance(),
					user.getBalance() - balance, benificiaryUser.getId(), user.getId());
			transactionService.saveTransaction(transactions);
			user.setBalance(user.getBalance() - balance);
			userService.saveUser(user);
//error in sending money from the same ac
			Transactions transactions2 = new Transactions(balance, "Credit", benificiaryUser.getBalance(),
					benificiaryUser.getBalance() + balance, user.getId(), benificiaryUser.getId());

			benificiaryUser.setBalance(benificiaryUser.getBalance() + balance);

			transactionService.saveTransaction(transactions2);

			userService.saveUser(user);
			session.setAttribute("updatedBal", "Updated balance is " + user.getBalance());
			userService.saveUser(benificiaryUser);
			session.setAttribute("msg", true);
			session.setAttribute("sentMsg", "Transaction Successful!!");
			return "redirect:index?sent";
		} else {
			session.setAttribute("msg", false);
			session.setAttribute("errorMsg", "Transaction declinded Due to Insufficient Funds!!");
			return "redirect:index?transactionError";
		}
	}

	@PostMapping("/deposit")
	public String deposit(@ModelAttribute("user") User user, HttpSession session) {
		if (user.getBalance() != 0) {
			User getUser = (User) session.getAttribute("user");
			Transactions tx = new Transactions(user.getBalance(), "Credit", getUser.getBalance(),
					getUser.getBalance() + user.getBalance(), user.getId(), getUser.getId());

			Transactions saveTransaction = transactionService.saveTransaction(tx);
			System.out.println(saveTransaction);
			getUser.setBalance(getUser.getBalance() + user.getBalance());
			session.setAttribute("newBalance", getUser.getBalance());
//			the foreign key will point to the latest transaction details row
			getUser.setTransaction(saveTransaction);
			userService.saveUser(getUser);
			session.setAttribute("deposit", true);
			session.setAttribute("depoMessage", "Money has been credited!!  ");
			return "redirect:index?deposited";
		}
		session.setAttribute("msg", false);
		session.setAttribute("errormsg", "Oops!! Something went wrong!");
		return "redirect:index?error";
	}

	@PostMapping("/withdrawl")
	public String withdrawl(@ModelAttribute("user") User user, HttpSession session) {
		User getUser = (User) session.getAttribute("user");
		if (getUser.getPassword().equals(user.getPassword())) {
			if (getUser.getBalance() >= user.getBalance()) {

				Transactions tx = new Transactions(user.getBalance(), "Debit", getUser.getBalance(),
						getUser.getBalance() - user.getBalance(), user.getId(), getUser.getId());

				Transactions saveTransaction = transactionService.saveTransaction(tx);
				System.out.println(saveTransaction);
				getUser.setBalance(getUser.getBalance() - user.getBalance());
				session.setAttribute("newBalance", getUser.getBalance());
//				the foreign key will point to the latest transaction details row
				getUser.setTransaction(saveTransaction);
				userService.saveUser(getUser);
				session.setAttribute("withdraw", true);
				session.setAttribute("withdrawMessage", "Money has been debited!!  ");
				return "redirect:/index?withdrawn";
			}
			session.setAttribute("msg", false);
			session.setAttribute("errormsg", "Opps! Insufficient funds");
			return "redirect:index?error";
		} else {
			session.setAttribute("msg", false);
			session.setAttribute("errormsg", "Opps! Incorrect Password");
			return "redirect:index?error";
		}

	}
//	@PostMapping("/findUserbyId")
//	public String findUserbyId(@RequestParam("userId") Long userId, HttpSession session) {
//		System.out.println(userId);
//		User user = userService.getUser(userId).orElse(null);
//		if (user != null) {
//			session.setAttribute("transferMsg", true);
//			session.setAttribute("userName", user.getFname()+" "+user.getLname());
//			session.setAttribute("msg", false);
//			return "redirect:/transfer";
//		}
//		session.setAttribute("notFound", "Oops!! No user found!!");
//		session.setAttribute("msg", false);
//		return "redirect:/index?notFound";
//	}
}

/*
 * When user click on the send money button then pop up a authentication form 👍
 * done after successful authentication redirect user into transfer page where
 * 👍 user done will enter the USER Id then in the right side of the input
 * filed👍 there will be a submit button named verify, when that user will click
 * in the👍 verify then the user id will come to the controller and will be
 * checked for👍 its existence and if false then will redirect to the transfer
 * page with alert👍 otherwise the name of that entered user id will be printed
 * and then there 👍 will be two method fast and fast cash. and one input amount
 * field. will check👍 for sufficient fund then only the operation will be
 * allowed otherwise alert with insufficient funds if successfully sent then
 * redirect to the index page with a alert sent with updated balance.
 */
