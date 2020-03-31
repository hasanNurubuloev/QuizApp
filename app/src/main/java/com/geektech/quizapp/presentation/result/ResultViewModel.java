package com.geektech.quizapp.presentation.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.model.QuizResult;

public class ResultViewModel extends ViewModel {
    MutableLiveData<QuizResult> quizResult = new MutableLiveData<>();

    public void getResult(Integer id) {
        quizResult.setValue(App.quizDatabase.historyDao().get(id));
    }
}
