package com.srs.quizapp.controller;

import com.srs.quizapp.model.Question;
import com.srs.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "allQuestions", produces = "application/json")
    public ResponseEntity<Iterable<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping(path = "category/{categoryValue}", produces = "application/json")
    public ResponseEntity<Iterable<Question>> getQuestionsByCategory(@PathVariable("categoryValue") String categoryName){
        return questionService.getQuestionsByCategory(categoryName);
    }

    @PostMapping(path = "add", consumes = "application/json")
    public ResponseEntity<Question> addQuestion(@RequestBody Question newQuestion){
        return questionService.addQuestion(newQuestion);
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    @PutMapping(path = "/", consumes = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }
}
