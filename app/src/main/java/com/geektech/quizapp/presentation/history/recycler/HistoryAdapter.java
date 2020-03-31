package com.geektech.quizapp.presentation.history.recycler;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    List<History> list;

    public HistoryAdapter() {
        list = new ArrayList<>();
    }

    public void update(List<History> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_history_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory, tvDate, tvDifficulty, tvCorrectAnswers;
        private  CardView cardView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.hh_tv_category);
            tvDifficulty = itemView.findViewById(R.id.hh_tv_difficulty);
            tvDate = itemView.findViewById(R.id.hh_tv_date);
            tvCorrectAnswers = itemView.findViewById(R.id.hh_tv_correct_answers);
            cardView = itemView.findViewById(R.id.card_view);
        }


        @SuppressLint("SetTextI18n")
        public void onBind(History history) {
            tvCategory.setText(R.string.category1+ history.getCategory());
            tvCorrectAnswers.setText(R.string.correct_answers+ history.getQuestionsAmount() + "/" + history.getCorrectAnswers());
            tvDifficulty.setText(R.string.difficulty_easy + history.getDifficulty());
            tvDate.setText(history.getCreatedAt().toString());

        }
    }
}
