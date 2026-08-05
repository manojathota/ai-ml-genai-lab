package com.google.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.example.service.IngestionService;

@RestController
public class ChatController {
	
	private final IngestionService ingestionService;

	public ChatController(IngestionService ingestionService) {
		this.ingestionService = ingestionService;
	}

	@GetMapping("/ask")
	public String ask(@RequestParam("question") String question) {
		return ingestionService.generateResponse(question);
	}

}
