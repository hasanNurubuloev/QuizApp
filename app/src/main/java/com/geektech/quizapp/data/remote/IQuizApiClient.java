package com.geektech.quizapp.data.remote;

import com.geektech.quizapp.model.Question;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(int amount, Integer category, String difficulty,QuestionsCallback callback);

   interface QuestionsCallback {
        void onSuccess(List<Question> questions);

        void onFailure(Exception e);
    }
}
