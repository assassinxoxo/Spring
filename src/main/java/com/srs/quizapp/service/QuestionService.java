package com.srs.quizapp.service;

import com.srs.quizapp.model.Question;
import com.srs.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<Iterable<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Iterable<Question>> getQuestionsByCategory(String categoryName) {
//        return questionDao.findByCategory(categoryName);
        try {
            return new ResponseEntity<>(questionDao.findByCategory(categoryName), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Question> addQuestion(Question newQuestion) {
//        return questionDao.save(newQuestion);
        try {
            return new ResponseEntity<>(questionDao.save(newQuestion), HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("id = " + id + "deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Question> updateQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }
}
