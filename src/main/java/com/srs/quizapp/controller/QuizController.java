package com.srs.quizapp.controller;

import com.srs.quizapp.model.QuestionWrapper;
import com.srs.quizapp.model.Quiz;
import com.srs.quizapp.model.SubmittedAnswer;
import com.srs.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(path = "create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    @PostMapping(path = "submit", consumes = "application/json")
    public ResponseEntity<List<SubmittedAnswer>> getRightAnswers(@RequestBody List<SubmittedAnswer> submittedAnswerList) {
        System.out.println("submittedAnswerList" + submittedAnswerList);
        return quizService.getRightAnswers(submittedAnswerList);
//        return new ResponseEntity<>("running", HttpStatus.OK);
    }
}
