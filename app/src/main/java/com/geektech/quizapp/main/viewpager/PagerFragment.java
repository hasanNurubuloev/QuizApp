package com.geektech.quizapp.main.viewpager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp.R;
import com.geektech.quizapp.history.HistoryFragment;
import com.geektech.quizapp.main.MainFragment;
import com.geektech.quizapp.main.MainViewModel;
import com.geektech.quizapp.settings.SettingsFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {
    private MainViewModel mViewModel;
    private MainFragment mainFragment;
    private HistoryFragment historyFragment;
    private SettingsFragment settingsFragment;

    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders
                .of(getActivity())
                .get(MainViewModel.class);

        }
    }

