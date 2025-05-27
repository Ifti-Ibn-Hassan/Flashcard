package com.flash.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flash.dao.FlashcardRepository;
import com.flash.dto.FlashcardResponse;
import com.flash.entities.Flashcard;
import com.flash.service.SubjectInferenceService;

@RestController
public class FlashcardController {

	@Autowired
	private FlashcardRepository flashcardRepository;

	@Autowired
	private SubjectInferenceService subjectInferenceService;

	// --- Task 1: Add Flashcard ---
	@PostMapping("/flashcard")
	public ResponseEntity<Map<String, String>> addFlashcard(@RequestBody Flashcard flashcard) {
		String subject = subjectInferenceService.inferSubject(flashcard.getQuestion());
		flashcard.setSubject(subject);
		flashcardRepository.save(flashcard);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Flashcard added successfully");
		response.put("subject", subject);
		return ResponseEntity.ok(response);
	}

	// --- Task 2: Get Mixed Flashcards ---
	@GetMapping("/get-subject")
	public ResponseEntity<List<FlashcardResponse>> getFlashcards(@RequestParam("student_id") String studentId,
			@RequestParam("limit") int limit) {

		List<Flashcard> allCards = flashcardRepository.findByStudentId(studentId);
		if (allCards.isEmpty()) {
			return ResponseEntity.ok(Collections.emptyList());
		}
		 
        Map<String, Queue<Flashcard>> grouped = new HashMap<>();
        for (Flashcard card : allCards) {
            grouped.computeIfAbsent(card.getSubject(), k -> new LinkedList<>()).add(card);
        }

		List<String> subjects = new ArrayList<>(grouped.keySet());
		Collections.shuffle(subjects);
		List<Flashcard> mixedFlashcards = new ArrayList<>();
		
		while (mixedFlashcards.size() < limit && !subjects.isEmpty()) {
	        Iterator<String> it = subjects.iterator();
	        while (it.hasNext() && mixedFlashcards.size() < limit) {
	            String subject = it.next();
	            Queue<Flashcard> queue = grouped.get(subject);
	            if (queue != null && !queue.isEmpty()) {
	                mixedFlashcards.add(queue.poll());
	            }
	            if (queue == null || queue.isEmpty()) {
	                it.remove();
	            }
	        }
	    }
		List<FlashcardResponse> response = mixedFlashcards.stream()
			        .map(fc -> new FlashcardResponse(fc.getQuestion(), fc.getAnswer(), fc.getSubject()))
			        .toList();
		 
		return ResponseEntity.ok(response);
	}

}
