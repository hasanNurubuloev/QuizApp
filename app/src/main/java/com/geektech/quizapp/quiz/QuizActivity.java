package com.geektech.quizapp.quiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.geektech.quizapp.R;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.data.remote.QuizApiClient;
import com.geektech.quizapp.model.Question;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
//    QuestionsResponse response = new QuestionsResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        QuizApiClient client = new QuizApiClient();
        client.getQuestions(new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
