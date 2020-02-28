package com.geektech.quizapp;

import android.app.Application;

import com.geektech.quizapp.data.QuizRepository;
import com.geektech.quizapp.data.local.QuizLocalDataSource;
import com.geektech.quizapp.data.remote.QuizRemoteDataSource;

public class App extends Application {

    //TODO: Create QuizRepository

    @Override
    public void onCreate() {
        super.onCreate();

        QuizLocalDataSource localDataSource = new QuizLocalDataSource();
        QuizRemoteDataSource remoteDataSource = new QuizRemoteDataSource();
        new QuizRepository(localDataSource, remoteDataSource);

    }

}
