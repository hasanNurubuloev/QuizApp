package com.geektech.quizapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp.model.History;
import com.geektech.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

//@Query("SELECT * FROM quiz_result WHERE id=:id")
//    QuizResult get(int id);

public class HistoryStorage {
    private HistoryDao mHistoryDao;


    public HistoryStorage(HistoryDao historyDao) {
        this.mHistoryDao = historyDao;
    }

    public int saveQuizResult(QuizResult quizResult) {
        return (int) mHistoryDao.insert(quizResult);

    }

    void deleteQuizResult(int id) {
        mHistoryDao.deleteById(id);
    }

    void deleteAllQuizResult() {
        mHistoryDao.deleteAll();
    }

    void getQuizResultById(int id) {
        mHistoryDao.get(id);
    }

    public LiveData<List<QuizResult>> getAll() {
        return mHistoryDao.getAll();
    }

    public LiveData<List<History>> getAllHistories() {
        return Transformations.map(getAll(), results -> {
            List<History> histories = new ArrayList<>();
            if (results.size() != 0) {
                for (int i = 0; i < results.size(); i++) {

                    histories.add(0, new History(results.get(i).getId()
                            , results.get(i).getCategory()
                            , results.get(i).getDifficulty()
                            , results.get(i).getQuestions().size()
                            , results.get(i).getCorrectAnswersAmount(),
                              results.get(i).getCreateAt()));
                }
            }
            return histories;
        });
    }
}
