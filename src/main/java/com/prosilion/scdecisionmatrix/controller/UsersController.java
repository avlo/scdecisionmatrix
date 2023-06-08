package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.dto.AppUserDto;
import com.prosilion.scdecisionmatrix.entity.AppUserAuthUser;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
	private final AppUserAuthUserService appUserAuthUserService;

	@Autowired
	public UsersController(AppUserAuthUserService appUserAuthUserService) {
		this.appUserAuthUserService = appUserAuthUserService;
	}

	@GetMapping("/users")
	public String users(Model model) {
		List<AppUserDto> users = getAppUserDto(appUserAuthUserService.findAllAppUsers());
		model.addAttribute("users", users);
		return "users";
	}

	private List<AppUserDto> getAppUserDto(List<AppUserAuthUser> users) {
		return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
	}

	private AppUserDto mapToUserDto(AppUserAuthUser appUserAuthUser) {
		AppUserDto userDto = new AppUserDto();
		userDto.setFirstName(appUserAuthUser.getAuthUserName());
		return userDto;
	}
}