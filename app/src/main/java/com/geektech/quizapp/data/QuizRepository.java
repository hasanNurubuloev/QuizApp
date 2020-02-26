package com.geektech.quizapp.data;

import com.geektech.quizapp.data.local.QuizLocalDataSource;
import com.geektech.quizapp.data.remote.QuizRemoteDataSource;

public class QuizRepository {
    private QuizLocalDataSource localDataSource;
    private QuizRemoteDataSource remoteDataSource;

    public QuizRepository(
            QuizLocalDataSource localDataSource,
            QuizRemoteDataSource remoteDataSource
    ) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

}
