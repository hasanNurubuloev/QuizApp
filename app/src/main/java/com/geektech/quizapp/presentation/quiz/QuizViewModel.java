package com.geektech.quizapp.presentation.quiz;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.data.QuizRepository;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private QuizRepository quizRepository = App.quizRepository;
    List<Question> questionList = new ArrayList<>();
    Integer count;

    MutableLiveData<List<Question>> retrofitData = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    public void loading() {
        isLoading.setValue(true);
    }

    public void query(int amount, Integer category, String difficulty) {
        App.quizRepository.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                isLoading.setValue(false);
                questionList.addAll(questions);
                Log.d("olololo", "onSuccess: "+ questionList.size());
                retrofitData.setValue(questions);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    private void finishQuiz() {
        QuizResult quizResult = new QuizResult(
                0,
                questionList.get(0).getCategory(),
                questionList.get(0).getDifficulty(),
                retrofitData.getValue(),
                getCorrectAnswersAmount(),
                new Date()
        );
        int resultId = quizRepository.saveQuizResult(quizResult);
        openResultEvent.setValue(resultId);
        finishEvent.call();
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        if (questionList.size() > position && position >= 0) {
            questionList.get(position).setSelectedAnswersPosition(selectedAnswerPosition);
            questionList.get(position).setAnswered(true);
            retrofitData.setValue(questionList);
            if (position + 1 == questionList.size()) {
                finishQuiz();

            } else {
                int seconds = 1;
                CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.e("olo", "onTick: ");
                    }

                    @Override
                    public void onFinish() {
                        currentQuestionPosition.setValue(++count);
                    }
                }.start();

            }
        }
    }

    private int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int i = 0; i < questionList.size()-1 ; i++) {
            if (questionList.get(i).getSelectedAnswersPosition() != null) {
                Log.d("olololo", "getCorrectAnswersAmount: " + questionList.size());
                String correctAnswer = questionList.get(i).getCorrectAnswer();
                String selectedAnswer = questionList.get(i).getAnswers()
                        .get(questionList.get(i).getSelectedAnswersPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }

        return correctAnswersAmount;
    }
    void onBackPress() {
        if (currentQuestionPosition.getValue() != 0) {
            currentQuestionPosition.setValue(--count);
        } else {
            finishEvent.call();
        }
    }
    void onSkip() {
        if (questionList.size() >= currentQuestionPosition.getValue()) {
            questionList.get(currentQuestionPosition.getValue()).setAnswered(true);
            retrofitData.setValue(questionList);
            currentQuestionPosition.setValue(++count);
            if (currentQuestionPosition.getValue() + 1 == questionList.size()) {
                finishQuiz();
            }
        }
    }
}
