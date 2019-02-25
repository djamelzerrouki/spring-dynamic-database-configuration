package com.example.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hsansbach.ecommerce.process.model.RegisterUserModel;

@Controller
@RequestMapping("/register")
public class RegisterUserController extends AbstractController {

	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@GetMapping()
	public String processes(@ModelAttribute RegisterUserModel registerUserModel) {
		return "registerUser";
	}

	@PostMapping("/user")
	public ModelAndView createUser(@Valid RegisterUserModel registerUserModel, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("registerUser", "formErrors", result.getAllErrors());
		}

		if (inMemoryUserDetailsManager.userExists(registerUserModel.getUser())) {
			// User already exists
			redirect.addFlashAttribute("globalMessage", "User " + registerUserModel.getUser() + " already exists.");
			return new ModelAndView("redirect:/register");
		}

		// User doesn't exists - create new user
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		UserDetails user = new User(registerUserModel.getUser(), registerUserModel.getPassword(), authorities);
		inMemoryUserDetailsManager.createUser(user);

		redirect.addFlashAttribute("globalMessage", "Successfully registered user " + registerUserModel.getUser() + ".");
		return new ModelAndView("redirect:/login");
	}
}
