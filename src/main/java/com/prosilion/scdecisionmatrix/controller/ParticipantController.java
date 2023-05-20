package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/participant")
public class ParticipantController {

	private final ParticipantService participantService;

	@Autowired
	public ParticipantController(ParticipantService participantService) {
		this.participantService = participantService;
	}

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
		System.out.println("333333333333333");
		System.out.println("333333333333333");
		participantService.save(participant);
		model.addAttribute("participant", participant);
		return "/participant/form";
	}
}
