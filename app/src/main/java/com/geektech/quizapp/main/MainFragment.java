package com.geektech.quizapp.main;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.geektech.quizapp.R;
import com.geektech.quizapp.core.CoreFragment;
import com.geektech.quizapp.quiz.QuizActivity;
import com.geektech.quizapp.utils.SimpleOnItemSelectedListener;
import com.geektech.quizapp.utils.SimpleOnSeekBarChangeListener;

import java.util.ArrayList;

public class MainFragment extends CoreFragment {

    private TextView tvValueSeekBar;
    private SeekBar seekBar;
    private Spinner spCategory;
    private Spinner spDifficulty;
    private Button btnStart;
    public String difficult;
    public Integer category;
    public int valueSeekBar;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        seekBar = view.findViewById(R.id.mf_seek_bar);
        int color = ContextCompat.getColor(getContext(), R.color.colorPurple);
        seekBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        seekBar.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        spCategory = view.findViewById(R.id.mf_spinner_category);
        spDifficulty = view.findViewById(R.id.mf_spinner_difficulty);
        btnStart = view.findViewById(R.id.btn_start);
        tvValueSeekBar = view.findViewById(R.id.mf_tv_value_seek);
        seekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                tvValueSeekBar.setText(String.valueOf(progress));
                //TODO valueSeekBar null
                valueSeekBar = progress;
                Log.d("ololo", "onProgressChanged: " + valueSeekBar);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDifficulty.setAdapter(adapter);

        spDifficulty.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                difficult = (String) parent.getSelectedItem();
                Log.d("ololo", "onItemSelected: " + difficult);
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter1);
        spCategory.setOnItemSelectedListener(new SimpleOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                category = parent.getSelectedItemPosition()+8;
            }
        });

        view.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              QuizActivity.start(getActivity(), difficult,category,valueSeekBar);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
