package com.geektech.quizapp.data;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp.data.local.HistoryStorage;
import com.geektech.quizapp.data.local.QuizLocalDataSource;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.data.remote.QuizApiClient;
import com.geektech.quizapp.model.History;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository {
    private QuizLocalDataSource localDataSource;
    private QuizApiClient quizApiClient;
    private List<String> answersList;
    private HistoryStorage historyStorage;

    public QuizRepository(
            HistoryStorage historyStorage,
            QuizApiClient remoteDataSource) {
        this.historyStorage = historyStorage;
        this.quizApiClient = remoteDataSource;
    }

    public int saveResult(QuizResult quizResult) {
        return historyStorage.saveQuizResult(quizResult);
    }

    public LiveData<List<History>> getAllHistories (){return historyStorage.getAllHistories();}

    private Question shuffleAnswers(Question question) {
        answersList = new ArrayList<>();
        answersList.addAll(question.getIncorrectAnswers());
        answersList.add(question.getCorrectAnswer());
        Collections.shuffle(answersList);
        question.setAnswers(answersList);
        //TODO: Shuffle answers
        return question;
    }

    public void getQuestions(int amount, Integer category, String difficulty, IQuizApiClient.QuestionsCallback callback) {
        quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                for (int i = 0; i < questions.size(); i++) {
                    Question question = shuffleAnswers(questions.get(i));
                    questions.set(i, question);
                }

                callback.onSuccess(questions);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }
    public int saveQuizResult(QuizResult quizResult) {
        return historyStorage.saveQuizResult(quizResult);
    }

}
