package com.geektech.quizapp.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private int mCounter = 0;
    private ArrayList<String> oper = new ArrayList<>();
    public MutableLiveData<ArrayList> operation = new MutableLiveData<>();
    public MutableLiveData<Integer> counter = new MutableLiveData<>();

    public void onIncrementClick() {
        mCounter++;
        oper.add("+");
        counter.setValue(mCounter);
        operation.setValue(oper);
    }

    public void onDecrementClick() {
        mCounter--;
        oper.add("-");
        counter.setValue(mCounter);
        operation.setValue(oper);

    }
}
