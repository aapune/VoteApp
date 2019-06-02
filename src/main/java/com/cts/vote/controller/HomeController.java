package com.cts.vote.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.vote.dao.RoleDao;
import com.cts.vote.domain.User;
import com.cts.vote.domain.VoteBean;
import com.cts.vote.domain.Votes;
import com.cts.vote.domain.security.UserRole;
import com.cts.vote.service.UserService;
import com.cts.vote.service.VoteService;
import com.cts.vote.validation.UserRegistrationValidator;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private VoteService voteService;

	@RequestMapping("/")
	public String redirectHome() {
		return "home";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/admin")
	public String admin(Model model, Principal principal) {
		List<VoteBean> listBean = voteService.getVotesSummary();
		model.addAttribute("voteList", listBean);
		return "admin";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model) {

		UserRegistrationValidator userValidator = new UserRegistrationValidator();
		List<String> errorList = userValidator.validate(user);
		if (errorList != null && errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			return "signup";
		}

		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {

			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}

			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}

			return "signup";
		} else {
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

			userService.createUser(user, userRoles);
			model.addAttribute("successSave", "Registered successfully VoteApp.");
			return "signup";
			// return "redirect:/";
		}
	}

	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
		VoteBean vBean = userService.prepareVoteBeanDTO();
		model.addAttribute("voteBean", vBean);
		return "userFront";
	}

	@RequestMapping(value = "/votesubmit", method = RequestMethod.POST)
	public String votesubmitPost(Principal principal, @ModelAttribute("voteBean") VoteBean vBean, Model model) {
		User user = userService.findByUsername(principal.getName());
		VoteBean vUpBean = userService.prepareVoteBeanDTO();
		boolean valSucess = voteService.validateVotesByUserIdAndSubId(user, vBean);
		if (valSucess) {
			Votes vote = voteService.convertToVoteObject(user, vBean);
			Votes updatedVote = voteService.saveVote(vote);
			if (updatedVote.getVoteid() != null) {
				model.addAttribute("voteBean", vUpBean);
				model.addAttribute("successVote", "Voted successfully.");
			}
		} else {
			model.addAttribute("voteBean", vUpBean);
			model.addAttribute("errorVote",
					"Error - Either you have used maximum voting attemps or not selected correct vote option.");
		}
		return "userFront";
	}
}
