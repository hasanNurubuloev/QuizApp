package com.geektech.quizapp.data;

import com.geektech.quizapp.data.local.QuizLocalDataSource;
import com.geektech.quizapp.data.remote.QuizApiClient;

public class QuizRepository {
    private QuizLocalDataSource localDataSource;
    private QuizApiClient remoteDataSource;

    public QuizRepository(
            QuizLocalDataSource localDataSource,
            QuizApiClient remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

}
