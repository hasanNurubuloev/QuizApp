package com.geektech.quizapp.presentation.quiz.recycler;

import android.annotation.SuppressLint;
import android.text.Html;
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
import com.geektech.quizapp.model.EType;
import com.geektech.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

import static com.geektech.quizapp.model.EType.MULTIPLE;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Question> list;
    private IClickAnswer clickAnswer;


    public QuizAdapter(IClickAnswer clickAnswer) {
        list = new ArrayList<>();
        this.clickAnswer = clickAnswer;

    }

    public void update(ArrayList<Question> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_quiz_holder, parent, false);
        QuizViewHolder holder = new QuizViewHolder(view, clickAnswer);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
            holder.onBind(list.get(position), position);
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
        private IClickAnswer clickAnswer;
        private int position;


        public QuizViewHolder(@NonNull View itemView, IClickAnswer clickAnswer) {
            super(itemView);
            init();
            this.clickAnswer = clickAnswer;
            setPos(clickAnswer);
        }

        private void init() {
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

        private void setPos(IClickAnswer clickAnswer) {
            btnFirstMp.setOnClickListener(v -> {
                clickAnswer.onClickAnswer(getAdapterPosition(), 0);
            });

            btnSecondMp.setOnClickListener(v -> {
                clickAnswer.onClickAnswer(getAdapterPosition(), 1);
            });

            btnThirdMp.setOnClickListener(v -> {
                clickAnswer.onClickAnswer(getAdapterPosition(), 2);
            });

            btnFourthMp.setOnClickListener(v -> {
                clickAnswer.onClickAnswer(getAdapterPosition(), 3);
            });

            btnFirstTf.setOnClickListener(v -> {
                clickAnswer.onClickAnswer(getAdapterPosition(), 0);
            });

            btnSecondTf.setOnClickListener(v -> {
                clickAnswer.onClickAnswer(getAdapterPosition(), 1);
            });

        }


        public void onBind(Question question, int position) {
            reset();
            if (question.isAnswered()) {
                setButtonsEnabled(false);
            } else {
                setButtonsEnabled(true);
            }
            tvQuestions.setText(Html.fromHtml(question.getQuestion()));
            if (question.getType() == "boolean") {
                linearLayoutMultiple.setVisibility(View.GONE);
                linearLayoutTrueFalse.setVisibility(View.VISIBLE);

                btnFirstTf.setText(Html.fromHtml(question.getAnswers().get(0)));
                btnSecondTf.setText(Html.fromHtml(question.getAnswers().get(1)));
            } else {
                linearLayoutMultiple.setVisibility(View.VISIBLE);
                linearLayoutTrueFalse.setVisibility(View.GONE);
                btnFirstMp.setText(Html.fromHtml(question.getIncorrectAnswers().get(0)));
                btnSecondMp.setText(Html.fromHtml(question.getIncorrectAnswers().get(1)));
                btnThirdMp.setText(Html.fromHtml(question.getIncorrectAnswers().get(2)));
                btnFourthMp.setText(Html.fromHtml(question.getCorrectAnswer()));
                Log.d("ololololo", "onBind: " + question.getAnswers().size());
                Log.d("ololololo", "onBind: " + question.getAnswers().get(2));
            }
            buttonsBg(question);
        }

        @SuppressLint("ResourceAsColor")
        public void reset() {
            btnFirstMp.setBackgroundResource(R.drawable.bg_button_answers);
            btnSecondMp.setBackgroundResource(R.drawable.bg_button_answers);
            btnThirdMp.setBackgroundResource(R.drawable.bg_button_answers);
            btnFourthMp.setBackgroundResource(R.drawable.bg_button_answers);
            btnFirstTf.setBackgroundResource(R.drawable.bg_button_answers);
            btnSecondTf.setBackgroundResource(R.drawable.bg_button_answers);

            btnFirstMp.setTextColor(itemView.getResources().getColor(R.color.blue));
            btnSecondMp.setTextColor(itemView.getResources().getColor(R.color.blue));
            btnThirdMp.setTextColor(itemView.getResources().getColor(R.color.blue));
            btnFourthMp.setTextColor(itemView.getResources().getColor(R.color.blue));
            btnFirstTf.setTextColor(itemView.getResources().getColor(R.color.blue));
            btnSecondTf.setTextColor(itemView.getResources().getColor(R.color.blue));
        }

        @SuppressLint("ResourceAsColor")
        public void buttonsBg(Question question) {
            if (question.getSelectedAnswersPosition() != null) {
                switch (question.getSelectedAnswersPosition()) {
                    case 0:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(0))) {
                            btnFirstMp.setBackgroundResource(R.drawable.correct_answer);
                            btnFirstTf.setBackgroundResource(R.drawable.correct_answer);
                            btnFirstMp.setTextColor(R.color.white);
                            btnFirstTf.setTextColor(R.color.white);
                        } else {
                            blueState(question);
                            btnFirstMp.setBackgroundResource(R.drawable.incorrect_answer);
                            btnFirstTf.setBackgroundResource(R.drawable.incorrect_answer);
                            btnFirstMp.setTextColor(R.color.white);
                            btnFirstTf.setTextColor(R.color.white);

                        }
                        break;
                    case 1:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(1))) {
                            btnSecondTf.setBackgroundResource(R.drawable.correct_answer);
                            btnSecondMp.setBackgroundResource(R.drawable.correct_answer);
                            btnSecondTf.setTextColor(R.color.white);
                            btnSecondMp.setTextColor(R.color.white);
                        } else {
                            blueState(question);
                            btnSecondTf.setBackgroundResource(R.drawable.incorrect_answer);
                            btnSecondMp.setBackgroundResource(R.drawable.incorrect_answer);
                            btnSecondTf.setTextColor(R.color.white);
                            btnSecondMp.setTextColor(R.color.white);

                        }
                        break;
                    case 2:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(2))) {
                            btnThirdMp.setBackgroundResource(R.drawable.correct_answer);
                            btnThirdMp.setTextColor(R.color.white);

                        } else {
                            blueState(question);
                            btnThirdMp.setBackgroundResource(R.drawable.incorrect_answer);
                            btnThirdMp.setTextColor(R.color.white);

                        }
                        break;
                    case 3:
                        if (question.getCorrectAnswer().equals(question.getAnswers().get(3))) {
                            btnFourthMp.setBackgroundResource(R.drawable.correct_answer);
                            btnFourthMp.setTextColor(R.color.white);
                        } else {
                            blueState(question);
                            btnFourthMp.setBackgroundResource(R.drawable.incorrect_answer);
                            btnFourthMp.setTextColor(R.color.white);

                        }
                        break;
                }
            }
        }

        public void setButtonsEnabled(boolean enabled) {
            btnFirstTf.setEnabled(enabled);
            btnSecondTf.setEnabled(enabled);
            btnFirstMp.setEnabled(enabled);
            btnSecondMp.setEnabled(enabled);
            btnThirdMp.setEnabled(enabled);
            btnFourthMp.setEnabled(enabled);
        }

        @SuppressLint("ResourceAsColor")
        void blueState(Question question) {
            if (question.getCorrectAnswer().equals(question.getAnswers().get(0))) {
                btnFirstMp.setBackgroundResource(R.drawable.selected_answer);
                btnFirstTf.setBackgroundResource(R.drawable.selected_answer);
                btnFirstMp.setTextColor(R.color.white);
                btnFirstTf.setTextColor(R.color.white);
            } else if (question.getCorrectAnswer().equals(question.getAnswers().get(1))) {
                btnSecondMp.setBackgroundResource(R.drawable.selected_answer);
                btnSecondTf.setBackgroundResource(R.drawable.selected_answer);
                btnSecondMp.setTextColor(R.color.white);
                btnSecondTf.setTextColor(R.color.white);
            } else if (question.getCorrectAnswer().equals(question.getAnswers().get(2))) {
                btnThirdMp.setBackgroundResource(R.drawable.selected_answer);
                btnThirdMp.setTextColor(R.color.white);
            } else {
                btnFourthMp.setBackgroundResource(R.drawable.selected_answer);
                btnFourthMp.setTextColor(R.color.white);
            }
        }
    }

}




