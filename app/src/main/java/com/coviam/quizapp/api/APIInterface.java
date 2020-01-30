package com.coviam.quizapp.api;

import com.coviam.quizapp.pojo.QuestionDTO;
import com.coviam.quizapp.pojo.SubscribedResponse;
import com.coviam.quizapp.pojo.UserPositions;
import com.coviam.quizapp.pojo.UserRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/home/getContest/subscribe/{userId}")
    Call<List<SubscribedResponse>> getSubscribedContent(@Path("userId") String userId);

    @GET("/home/{userId}")
    Call<List<SubscribedResponse>> getLeaderboardContent(@Path("userId") String userId);

    @GET("/home/{contentId}")
    Call<List<UserPositions>> getLeaderboardPosition(@Path("contentId") String contentId);

    /*@GET("/contest/{contestId")
    Call<List<QuestionDTO>> getQuestions(@Path("contestId") String contestId);

     */

    @GET("/bins/rk66a")
    Call<List<QuestionDTO>> getQuestions();


}
