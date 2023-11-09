package com.srs.quizapp.service;

import com.srs.quizapp.model.Question;
import com.srs.quizapp.model.QuestionWrapper;
import com.srs.quizapp.model.Quiz;
import com.srs.quizapp.dao.QuestionDao;
import com.srs.quizapp.dao.QuizDao;
import com.srs.quizapp.model.SubmittedAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.getRandomQuestionsBasedOnCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        System.out.println("quiz: " + quiz);
        quizDao.save(quiz);
        return new ResponseEntity<>("Successfully added the new quiz", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<List<SubmittedAnswer>> getRightAnswers(List<SubmittedAnswer> submittedAnswerList) {
        List<SubmittedAnswer> rightAnswersList = new ArrayList<>();
        for(SubmittedAnswer sa: submittedAnswerList){
            if(sa.getAnswer().equals(questionDao.getReferenceById(sa.getId()).getRightAnswer()) ){
                System.out.println(sa.getId() + " - has right answer");
                rightAnswersList.add(sa);
            }
        }
        return new ResponseEntity<>(rightAnswersList,HttpStatus.OK);
    }
}
