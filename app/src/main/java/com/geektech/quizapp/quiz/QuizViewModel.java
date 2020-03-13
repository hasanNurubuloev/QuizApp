package com.geektech.quizapp.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.data.remote.QuizApiClient;
import com.geektech.quizapp.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public MutableLiveData<List<Question>> retrofitData = new MutableLiveData<>();


    public void query(int amount, Integer category, String difficulty) {
        QuizApiClient quizApiClient = new QuizApiClient();
        quizApiClient.getQuestions( amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                retrofitData.setValue(questions);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
