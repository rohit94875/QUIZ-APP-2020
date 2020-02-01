package com.coviam.quizapp.api;

import com.coviam.quizapp.pojo.AnswerDTO;
import com.coviam.quizapp.pojo.LeaderBoardDetails;
import com.coviam.quizapp.pojo.QuestionDTO;
import com.coviam.quizapp.pojo.Response;
import com.coviam.quizapp.pojo.SubscribedResponse;
import com.coviam.quizapp.pojo.UserPositions;
import com.coviam.quizapp.pojo.UserRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("contest/getContestsOfAUser/{userId}")
    Call<List<SubscribedResponse>> getSubscribedContent(@Path("userId") String userId);

    @GET("/home/{userId}")
    Call<List<SubscribedResponse>> getLeaderboardContent(@Path("userId") String userId);

    @POST("/report/static/user/get")
    Call<List<UserPositions>> getLeaderboardPosition(@Body LeaderBoardDetails leaderBoardDetails);

    /*@GET("/contest/{contestId")
    Call<List<QuestionDTO>> getQuestions(@Path("contestId") String contestId);
     */
    @GET("contest/getContest/{contestId)")
    Call<List<QuestionDTO>> getQuestions(@Path("contestId") String contestId);

    @POST("/contset/static/submit")
    Call<Response> submitAnswers(@Body AnswerDTO answerDTO);


}
