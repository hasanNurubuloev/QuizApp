package com.geektech.quizapp.data.local;

import androidx.lifecycle.LiveData;


import com.geektech.quizapp.model.QuizResult;

import java.util.List;

//@Query("SELECT * FROM quiz_result WHERE id=:id")
//    QuizResult get(int id);

public class HistoryStorage {
    private HistoryDao mHistoryDao;

    public HistoryStorage(HistoryDao historyDao) {
        this.mHistoryDao = historyDao;
    }
    void saveQuizResult(QuizResult quizResult) {
        mHistoryDao.insert(quizResult);
    }

    void deleteQuizResult(int id) {
        mHistoryDao.deleteById(id);
    }

    void deleteAllQuizResult() {
        mHistoryDao.deleteAll();
    }
    void getQuizResultById (int id ) {
        mHistoryDao.get(id);
    }
    public LiveData<List<QuizResult>> getAll(){
        return mHistoryDao.getAll();
    }
}
