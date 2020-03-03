package com.geektech.quizapp;

import android.app.Application;

import com.geektech.quizapp.data.QuizRepository;
import com.geektech.quizapp.data.local.QuizLocalDataSource;
import com.geektech.quizapp.data.remote.QuizApiClient;

public class App extends Application {

    //TODO: Create QuizRepository
public static QuizRepository quizRepository;
    @Override
    public void onCreate() {
        super.onCreate();

        QuizLocalDataSource localDataSource = new QuizLocalDataSource();
        QuizApiClient remoteDataSource = new QuizApiClient();
        quizRepository =new QuizRepository(localDataSource, remoteDataSource);

    }

}
