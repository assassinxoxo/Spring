package com.srs.quizapp.model;

import com.srs.quizapp.model.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToMany
    private List<Question> questions;
}
