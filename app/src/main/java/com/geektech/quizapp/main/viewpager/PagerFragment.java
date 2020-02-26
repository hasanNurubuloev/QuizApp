package com.geektech.quizapp.main.viewpager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp.R;
import com.geektech.quizapp.main.MainViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {
    private Button increm, decrem;
    private TextView counter;

    private MainViewModel mViewModel;

    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        increm = view.findViewById(R.id.increm);
        decrem = view.findViewById(R.id.decrem);
        counter = view.findViewById(R.id.counter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders
                .of(getActivity())
                .get(MainViewModel.class);
        int position = getArguments().getInt("pos");
        switch (position) {
            case 0:
                counter.setVisibility(View.GONE);
                break;
            case 1:
                increm.setVisibility(View.GONE);
                decrem.setVisibility(View.GONE);
                counter.setVisibility(View.VISIBLE);
                mViewModel.counter.observe(getActivity(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        counter.setText(integer.toString());
                    }
                });
                break;
            case 2:
                increm.setVisibility(View.GONE);
                decrem.setVisibility(View.GONE);
                mViewModel.operation.observe(getActivity(), new Observer<ArrayList>() {
                    @Override
                    public void onChanged(ArrayList arrayList) {
                        counter.setText(String.valueOf(arrayList));
                    }
                });
                break;

        }
        decrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.onDecrementClick();
            }
        });
        increm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.onIncrementClick();
            }
        });
    }
}
