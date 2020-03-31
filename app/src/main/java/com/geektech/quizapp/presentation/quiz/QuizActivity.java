package com.geektech.quizapp.presentation.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.presentation.quiz.recycler.IClickAnswer;
import com.geektech.quizapp.presentation.quiz.recycler.QuizAdapter;
import com.geektech.quizapp.presentation.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements IClickAnswer {
    private static final String EXTRA_AMOUNT = "amount";
    private static final String EXTRA_CATEGORY = "category";
    private static final String EXTRA_DIFFICULTY = "difficulty";
    private ProgressBar progressBar;
    private TextView tvValueProgress;
    private TextView tvTitle;
    private LottieAnimationView lottieAnimationView;
    private String difficulty;
    private Integer category;
    private int amount;
    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private ArrayList<Question> list;
    private Button btnBack;
    private ConstraintLayout activityQuiz;
    List<Question> listQuestion = new ArrayList<>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        lottieAnimationView = findViewById(R.id.loading_lottie);
        tvValueProgress = findViewById(R.id.qa_tv_value_progress);
        progressBar = findViewById(R.id.progress_bar_question);
        activityQuiz = findViewById(R.id.activity_quiz);
        tvTitle = findViewById(R.id.qa_tv_title);

        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);

        Intent intent = getIntent();
        amount = intent.getIntExtra(EXTRA_AMOUNT, 1);
        difficulty = intent.getStringExtra(EXTRA_DIFFICULTY);
        category = intent.getIntExtra(EXTRA_CATEGORY, 1);

        progressBar.setProgress(amount);
//        tvValueProgress.setText("1/" + amount);

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
                listQuestion = questions;
                getPosition();
            }
        });


        quizViewModel.isLoading.observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean isLoading) {
                        if (isLoading) {
                            activityQuiz.setVisibility(View.GONE);
                            lottieAnimationView.setVisibility(View.VISIBLE);
                        } else {
                            lottieAnimationView.setVisibility(View.GONE);
                            activityQuiz.setVisibility(View.VISIBLE);
                        }
                    }
                });

        quizViewModel.finishEvent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                finish();
            }
        });
        quizViewModel.openResultEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ResultActivity.start(QuizActivity.this, integer);
            }
        });
        quizViewModel.loading();



        btnBack = findViewById(R.id.qa_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizViewModel.onBackPress();
            }
        });

        findViewById(R.id.qa_btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizViewModel.onSkip();
            }
        });

    }
    public void getPosition() {
        quizViewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer);
                progressBar.setProgress(integer + 1);
                progressBar.setMax(amount);
                tvTitle.setText(listQuestion.get(integer).getCategory());
            }
        });
    }
    @Override
    public void onClickAnswer(int pos, int selectedPos) {
        quizViewModel.onAnswerClick(pos, selectedPos);
    }

    public static void start(Context context, String difficulty, Integer category, int amount) {
        Intent starter = new Intent(context, QuizActivity.class);
        starter.putExtra(EXTRA_DIFFICULTY, difficulty);
        starter.putExtra(EXTRA_CATEGORY, category);
        starter.putExtra(EXTRA_AMOUNT, amount);
        context.startActivity(starter);

    }
}
