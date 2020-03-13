package com.geektech.quizapp.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.quiz.quizRecycler.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private String difficulty;
    private Integer category;
    private int amount;
    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private ArrayList<Question> list;
    private Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new QuizAdapter();
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        amount = intent.getIntExtra("amount", 1);
        difficulty = intent.getStringExtra("difficulty");
        category = intent.getIntExtra("category", 1);



        quizViewModel = ViewModelProviders
                .of(this)
                .get(QuizViewModel.class);
        if (category == 8) category = null;
        if (difficulty.equals("Any difficulty")) difficulty = null;
        quizViewModel.query(amount, category, difficulty);


        quizViewModel.retrofitData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.update((ArrayList<Question>) questions);
            }
        });

        btnBack = findViewById(R.id.qa_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public static void start(Context context, String difficulty, Integer category, int amount) {
        Intent starter = new Intent(context, QuizActivity.class);
        starter.putExtra("difficulty", difficulty);
        starter.putExtra("category", category);
        starter.putExtra("amount", amount);
        context.startActivity(starter);
    }
}
