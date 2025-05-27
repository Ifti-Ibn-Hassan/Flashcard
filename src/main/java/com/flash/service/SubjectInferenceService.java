package com.flash.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SubjectInferenceService {

	private final Map<String, List<String>> subjectKeywords = Map.of(
	        "Biology", List.of("photosynthesis", "cell", "evolution", "organism", "mitosis"),
	        "Physics", List.of("velocity", "acceleration", "force", "energy", "newton", "motion"),
	        "Math", List.of("algebra", "equation", "calculus", "geometry", "integral", "derivative"),
	        "Chemistry", List.of("atom", "molecule", "reaction", "acid", "base", "compound"),
	        "History", List.of("war", "revolution", "empire", "ancient", "king", "treaty")
	    );

	    public String inferSubject(String text) {
	        String lower = text.toLowerCase();
	        for (var entry : subjectKeywords.entrySet()) {
	            for (String keyword : entry.getValue()) {
	                if (lower.contains(keyword)) {
	                    return entry.getKey();
	                }
	            }
	        }
	        return "General";
	    }
	
}
