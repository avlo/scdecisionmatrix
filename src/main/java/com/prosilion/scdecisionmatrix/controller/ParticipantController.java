package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Participant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/participant")
public class ParticipantController {

	@GetMapping("/")
	public String index() {
    System.out.println("0000000000000");
		System.out.println("0000000000000");
		return "redirect:/participant/form";
	}

	@GetMapping("/form")
	public String formGet() {
		System.out.println("11111111111111");
		System.out.println("11111111111111");
		return "/participant/form";
	}

	@PostMapping("/form")
	public String formPost(Participant participant, Model model) {
		System.out.println("222222222222222");
		System.out.println("222222222222222");
		model.addAttribute("participant", participant);
		return "/participant/form";
	}
}
