package com.flash.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flash.entities.Flashcard;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

	List<Flashcard> findByStudentId(String studentId);
	
}
