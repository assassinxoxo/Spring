package com.srs.quizapp.dao;

import com.srs.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    Iterable<Question> findByCategory(String category);
}
