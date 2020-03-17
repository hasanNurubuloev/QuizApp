package com.geektech.quizapp.model;

import java.util.Date;

public class History {

    private int id;

    private String category;

    private String difficulty;

    private int questionsAmount;

    private int correctAnswers;

    private Date createdAt;

    public History(int id, String category, String difficulty, int questionsAmount, int correctAnswers, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questionsAmount = questionsAmount;
        this.correctAnswers = correctAnswers;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestionsAmount() {
        return questionsAmount;
    }

    public void setQuestionsAmount(int questionsAmount) {
        this.questionsAmount = questionsAmount;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
