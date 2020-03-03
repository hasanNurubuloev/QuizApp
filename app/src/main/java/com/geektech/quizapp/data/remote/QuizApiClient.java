package com.geektech.quizapp.data.remote;

import com.geektech.quizapp.core.CoreCallback;
import com.geektech.quizapp.main.MainFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    private MainFragment mainFragment = new MainFragment();


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(final QuestionsCallback callback) {
//        Call<QuestionsResponse> call = client.getQuestions(10,"sport", "multiply", "easy");
        Call<QuestionsResponse> call = client.getQuestions(mainFragment.valueSeekBar, mainFragment.category, "Any Type", mainFragment.difficult);


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
                @Query("category") String category,
                @Query("type") String type,
                @Query("difficulty") String difficulty
        );
    }
}
