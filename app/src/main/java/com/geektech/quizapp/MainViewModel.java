package com.geektech.quizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private int mCounter = 0;
    private ArrayList<String> oper = new ArrayList<>();
    public MutableLiveData<ArrayList> operation = new MutableLiveData<>();
    public MutableLiveData<Integer> counter = new MutableLiveData<>();

    void onIncrementClick() {
        mCounter++;
        oper.add("+");
        counter.setValue(mCounter);
        operation.setValue(oper);
    }

    void onDecrementClick() {
        mCounter--;
        oper.add("-");
        counter.setValue(mCounter);
        operation.setValue(oper);

    }
}
