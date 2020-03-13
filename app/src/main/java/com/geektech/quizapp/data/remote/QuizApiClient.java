package com.geektech.quizapp.data.remote;

import android.util.Log;

import com.geektech.quizapp.main.MainFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    MainFragment mainFragment = MainFragment.newInstance();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(int amount, Integer category, String difficulty,final QuestionsCallback callback) {
        mainFragment.valueSeekBar= amount;
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

    private interface QuizApi {
        @GET("/api.php")
        Call<QuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );
    }
}
