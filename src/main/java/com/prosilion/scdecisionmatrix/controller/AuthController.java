package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.ContractuserAuthuser;
import com.prosilion.scdecisionmatrix.entity.UserDto;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.ContractUserAuthUserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private final AuthUserDetailsService authUserDetailsService;
	private final ContractUserAuthUserService contractUserAuthUserService;

	public AuthController(AuthUserDetailsService authUserDetailsService,
			ContractUserAuthUserService contractUserAuthUserService) {
		this.authUserDetailsService = authUserDetailsService;
		this.contractUserAuthUserService = contractUserAuthUserService;
	}

	@GetMapping("/index")
	public String home(){
		return "index";
	}

	// handler method to handle login request
	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		LOGGER.info("AuthController 0000000000000000000000000000");
		LOGGER.info("AuthController 0000000000000000000000000000");
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
		LOGGER.info("AuthController 11111111111111111111111111");
		LOGGER.info("AuthController 11111111111111111111111111");
		ContractuserAuthuser contractuserAuthuser = contractUserAuthUserService.createUser(userDto);
//		AuthUserDetails existingUser = authUserDetailsService.loadUserByUserDto(userDto);

		// TODO: need to check for existing user, right now, jsut create new user every time
//		if(existingUser. != null){
//			result.rejectValue("username", null,
//					"There is already an account registered with the same username");
//		}

		if(result.hasErrors()){
			model.addAttribute("user", userDto);
			return "/register";
		}

//		contractUserAuthUserService.createUser(userDto);
		return "redirect:/register?success";
	}

//	@GetMapping("/users")
//	public String users(Model model){
//		List<UserDto> users = userService.findAllUsers();
//		model.addAttribute("users", users);
//		return "users";
//	}
}
