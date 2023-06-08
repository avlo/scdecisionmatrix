package com.prosilion.scdecisionmatrix.security.controller;

import com.prosilion.scdecisionmatrix.dto.AppUserDto;
import com.prosilion.scdecisionmatrix.entity.AppuserAuthuser;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
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
	private final AppUserAuthUserService appUserAuthUserService;

	public AuthController(AuthUserDetailsService authUserDetailsService,
			AppUserAuthUserService appUserAuthUserService) {
		this.authUserDetailsService = authUserDetailsService;
		this.appUserAuthUserService = appUserAuthUserService;
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
		AppUserDto user = new AppUserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@ModelAttribute("user") AppUserDto appUserDto, BindingResult result, Model model){
		LOGGER.info("AuthController 11111111111111111111111111");
		LOGGER.info("AuthController 11111111111111111111111111");
		AppuserAuthuser appuserAuthuser = appUserAuthUserService.createUser(appUserDto);
//		AuthUserDetails existingUser = authUserDetailsService.loadUserByUserDto(appUserDto);

		// TODO: need to check for existing user, right now, jsut create new user every time
//		if(existingUser. != null){
//			result.rejectValue("username", null,
//					"There is already an account registered with the same username");
//		}

		if(result.hasErrors()){
			model.addAttribute("user", appUserDto);
			return "/register";
		}

//		appUserAuthUserService.createUser(appUserDto);
		return "redirect:/register?success";
	}

//	@GetMapping("/users")
//	public String users(Model model){
//		List<AppUserDto> users = userService.findAllUsers();
//		model.addAttribute("users", users);
//		return "users";
//	}
}
