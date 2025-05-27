package com.flash.dto;

public class FlashcardResponse {

	private String question;
    private String answer;
    private String subject;

    public FlashcardResponse(String question, String answer, String subject) {
        this.question = question;
        this.answer = answer;
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSubject() {
        return subject;
    }
    
}
