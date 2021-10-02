package com.example.FinalQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FinalQuiz.domain.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	
	
}