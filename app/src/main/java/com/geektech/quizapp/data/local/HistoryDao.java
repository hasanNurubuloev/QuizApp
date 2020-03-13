package com.geektech.quizapp.data.local;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geektech.quizapp.model.QuizResult;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM quiz_result  WHERE id=:id")
    QuizResult get(int id);

    @Query("DELETE FROM quiz_result WHERE id=:id")
    void deleteById(int id);

    @Query("DELETE FROM quiz_result")
    void deleteAll();

    @Query("SELECT * FROM quiz_result")
    LiveData<List<QuizResult>> getAll();
}
