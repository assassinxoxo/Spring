package com.srs.quizapp.model;

import lombok.Data;

@Data
public class SubmittedAnswer {
    private int id;
    private String answer;

    public SubmittedAnswer(int id, String rightAnswer) {
        this.id = id;
        this.answer = rightAnswer;
    }
}
