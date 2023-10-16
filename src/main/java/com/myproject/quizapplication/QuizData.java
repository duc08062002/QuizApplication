package com.myproject.quizapplication;

import java.util.List;

public class QuizData {
    private String question;
    private List<String> answers;
    private int correctAnswerIndex;

    public QuizData(String question, List<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
