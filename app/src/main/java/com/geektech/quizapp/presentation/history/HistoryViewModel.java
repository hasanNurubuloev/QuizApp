package com.geektech.quizapp.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.model.History;
import com.geektech.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    LiveData<List<History>> history = App.quizRepository.getAllHistories();

}
