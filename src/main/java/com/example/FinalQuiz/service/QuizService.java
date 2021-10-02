package com.example.FinalQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FinalQuiz.domain.Quiz;
import com.example.FinalQuiz.repository.QuizRepository;

@Service
public class QuizService {

	
	@Autowired
    private QuizRepository repo;
	
	public List<Quiz> listAll() {
        return repo.findAll();
    }
     
    public void save(Quiz std) {
        repo.save(std);
    }
     
    public Quiz get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
	
}
