package com.google.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {

	private static final Logger LOG = LoggerFactory.getLogger(IngestionService.class);

	private final ChatModel chatModel;

	public IngestionService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String generateResponse(String prompt) {
		return chatModel.call(prompt);
	}

}
