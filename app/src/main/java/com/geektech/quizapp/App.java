package com.geektech.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.geektech.quizapp.data.QuizRepository;
import com.geektech.quizapp.data.local.HistoryStorage;
import com.geektech.quizapp.data.local.QuizDatabase;
import com.geektech.quizapp.data.local.QuizLocalDataSource;
import com.geektech.quizapp.data.remote.QuizApiClient;

public class App extends Application {

    //TODO: Create QuizRepository
public static QuizRepository quizRepository;
    public static QuizDatabase quizDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        quizDatabase = Room.databaseBuilder(this, QuizDatabase.class, "quiz"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();


        HistoryStorage historyStorage= new HistoryStorage(quizDatabase.historyDao());
        QuizApiClient quizApiClient = new QuizApiClient();
        quizRepository =new QuizRepository(historyStorage,quizApiClient);

    }

}
