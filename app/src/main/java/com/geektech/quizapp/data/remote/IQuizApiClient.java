package com.geektech.quizapp.data.remote;

import com.geektech.quizapp.model.GlobalResponse;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.QuizQuestionCount;
import com.geektech.quizapp.model.TriviaCategories;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(int amount, Integer category, String difficulty,QuestionsCallback callback);


    void getGlobal(GlobalCallback globalCallback);

    void getQuestionResponse(Integer category,QuestionResponseCallback questionResponseCallback);

    void getTriviaCategoryCallback(TriviaCategoryCallback triviaCategoryCallback);
   interface QuestionsCallback {
        void onSuccess(List<Question> questions);

        void onFailure(Exception e);
    }
    interface GlobalCallback {
        void onSuccess(GlobalResponse globalResponse);

        void onFailure(Throwable e);
    }

    interface QuestionResponseCallback  {
        void onSuccess(QuizQuestionCount quizQuestionCount);

        void onFailure(Throwable e);
   }

    interface TriviaCategoryCallback  {
        void onSuccess(TriviaCategories categories);

        void onFailure(Throwable e);
    }


}
