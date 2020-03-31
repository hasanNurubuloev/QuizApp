package com.geektech.quizapp.data.remote;

import android.util.Log;

import com.geektech.quizapp.model.GlobalResponse;
import com.geektech.quizapp.model.QuizQuestionCount;
import com.geektech.quizapp.model.TriviaCategories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, final QuestionsCallback callback) {
        Call<QuestionsResponse> call = client.getQuestions(amount, category, difficulty);
//        Call<QuestionsResponse> call = client.getQuestions(mainFragment.valueSeekBar, mainFragment.category, mainFragment.difficult);
        Log.d("ololo", "getQuestions: " + amount);


        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body().getResults());
                    } else {
                        callback.onFailure(new Exception("Body is empty"));
                    }
                } else {
                    callback.onFailure(new Exception("Response error " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    @Override
    public void getGlobal(GlobalCallback globalCallback) {
        Call<GlobalResponse> call = client.getGlobal();
        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                globalCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {
                globalCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getQuestionResponse(Integer category, QuestionResponseCallback questionResponseCallback) {
        Call<QuizQuestionCount> call = client.getQuestionCount(category);
        call.enqueue(new Callback<QuizQuestionCount>() {
            @Override
            public void onResponse(Call<QuizQuestionCount> call, Response<QuizQuestionCount> response) {
                questionResponseCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<QuizQuestionCount> call, Throwable t) {
                questionResponseCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getTriviaCategoryCallback(TriviaCategoryCallback triviaCategoryCallback) {
        Call<TriviaCategories> call = client.getTriviaCategories();
        call.enqueue(new Callback<TriviaCategories>() {
            @Override
            public void onResponse(Call<TriviaCategories> call, Response<TriviaCategories> response) {
                triviaCategoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TriviaCategories> call, Throwable t) {
                triviaCategoryCallback.onFailure(t);
            }
        });
    }

    private interface QuizApi {
        @GET("/api.php")
        Call<QuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<TriviaCategories> getTriviaCategories();

        @GET("api_count_global.php")
        Call<GlobalResponse> getGlobal();

        @GET("api_count.php?category")
        Call<QuizQuestionCount> getQuestionCount(
                @Query("category") Integer category);

    }
}
