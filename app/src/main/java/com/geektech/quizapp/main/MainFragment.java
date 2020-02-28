package com.geektech.quizapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp.R;
import com.geektech.quizapp.utils.SimpleOnItemSelectedListener;
import com.geektech.quizapp.utils.SimpleOnSeekBarChangeListener;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    TextView tv_valueSeekBar;
    SeekBar seekBar;
    Spinner sp_category;
    Spinner sp_difficulty;
    Button btn_start;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        seekBar = view.findViewById(R.id.mf_seek_bar);
        sp_category = view.findViewById(R.id.mf_spinner_category);
        sp_difficulty = view.findViewById(R.id.mf_spinner_difficulty);
        btn_start = view.findViewById(R.id.btn_start);
        tv_valueSeekBar = view.findViewById(R.id.mf_tv_value_seek);
        seekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_valueSeekBar.setText(String.valueOf(progress));
                super.onProgressChanged(seekBar, progress, fromUser);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_difficulty.setAdapter(adapter);
        sp_difficulty.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                super.onItemSelected(parent, view, position, id);
            }
        });


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(adapter1);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders
                .of(getActivity())
                .get(MainViewModel.class);
    }

}
