package com.spring.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {

	private final VectorStore vectorStore;

	private static final Logger LOG = LoggerFactory.getLogger(IngestionService.class);

	public IngestionService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}

	@EventListener(ApplicationStartedEvent.class)
	public void ingest() {
		try (InputStream in = new FileInputStream(new File("./src/docs/text.txt"))) {
			List<Document> docs = List.of(new Document(new String(in.readAllBytes())));
			vectorStore.add(docs);
		} catch (FileNotFoundException e) {
			LOG.error("Error while ingesting the files to PG vector", e);
		} catch (IOException e) {
			LOG.error("Error while ingesting the files to PG vector", e);
		}
	}

}
