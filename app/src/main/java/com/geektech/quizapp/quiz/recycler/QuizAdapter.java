package com.geektech.quizapp.quiz.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Question> list;
    private int amount;


    public QuizAdapter() {
        list = new ArrayList<>();

    }

    public void update(ArrayList<Question> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_quiz_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("ololo", "getItemCount: " + list.size());
        return list.size();
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {
        private TextView tvQuestions, tvTitle;
        private Button btnFirstMp, btnSecondMp, btnThirdMp, btnFourthMp, btnFirstTf, btnSecondTf;
        private LinearLayout linearLayoutMultiple, linearLayoutTrueFalse;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestions = itemView.findViewById(R.id.qh_tv_question);
            btnFirstMp = itemView.findViewById(R.id.btn_first_mp);
            btnSecondMp = itemView.findViewById(R.id.btn_second_mp);
            btnThirdMp = itemView.findViewById(R.id.btn_third_mp);
            btnFourthMp = itemView.findViewById(R.id.btn_fourth_mp);
            btnFirstTf = itemView.findViewById(R.id.btn_first_tf);
            btnSecondTf = itemView.findViewById(R.id.btn_second_tf);
            linearLayoutMultiple = itemView.findViewById(R.id.linear_multiply);
            linearLayoutTrueFalse = itemView.findViewById(R.id.linear_true_false);
        }

        public void onBind(Question question) {
            tvQuestions.setText(question.getQuestion());
            if (question.getType() == "multiple") {
                linearLayoutTrueFalse.setVisibility(View.GONE);
                linearLayoutMultiple.setVisibility(View.VISIBLE);
                btnFirstMp.setText(question.getIncorrectAnswers().get(0));
                btnSecondMp.setText(question.getIncorrectAnswers().get(1));
                btnThirdMp.setText(question.getIncorrectAnswers().get(2));
                btnFourthMp.setText(question.getCorrectAnswer());
            } else {
                linearLayoutMultiple.setVisibility(View.GONE);
                linearLayoutTrueFalse.setVisibility(View.VISIBLE);
                btnFirstTf.setText(question.getCorrectAnswer());
                btnSecondTf.setText(question.getIncorrectAnswers().get(0));
                Log.d("ololo", "onBind: " + question.getQuestion());
            }
        }
    }
}

