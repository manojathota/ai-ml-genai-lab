package com.spring.example.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {

	private final ChatClient chatClient;
	private final VectorStore vectorStore;

	public RagController(ChatClient.Builder builder, VectorStore vectorStore) {
		this.chatClient = builder.build();
		this.vectorStore = vectorStore;
	}

	@GetMapping("/rag")
	public String rag(@RequestParam("question") String question) {
		List<Document> docs = vectorStore.similaritySearch(SearchRequest.builder().query(question).topK(3).build());
		String context = docs.stream().map(Document::getText).collect(Collectors.joining("\n---\n"));
		return chatClient.prompt().system("use following context to answer: \n" + context).user(question).call()
				.content();
	}

}
