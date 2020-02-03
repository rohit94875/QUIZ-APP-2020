package api;

import java.util.List;

import okhttp3.ResponseBody;

import com.coviam.quizapp.pojo.AdResponse;
import com.coviam.quizapp.pojo.Analytics;
import com.coviam.quizapp.pojo.Category;
import com.coviam.quizapp.pojo.ContestList;
import com.coviam.quizapp.pojo.Login;
import com.coviam.quizapp.pojo.LoginResponse;
import com.coviam.quizapp.pojo.LoginToken;
import com.coviam.quizapp.pojo.Profile;
import com.coviam.quizapp.pojo.Provider;
import com.coviam.quizapp.pojo.Register;
import com.coviam.quizapp.pojo.Role;
import com.coviam.quizapp.pojo.RoleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/contentservice/getCategories")
    Call<List<Category>> getCategories();

    @GET("/contest/getStaticContestsByCategory/{categoryId}")
    Call<List<ContestList>> getContests(@Path("categoryId") String categoryId);

    @GET("/contest/getDynmicContestByCategory/{categoryId}")
    Call<List<ContestList>> getDynamicContests(@Path("categoryId") String categoryId);


    @GET("/userRecord/get/{userId}")
    Call<Profile> getPoints(@Path("userId") String userId);

    @POST("/auth/signin")
    Call<LoginToken> login(@Body Login login);

    @POST("/auth/signup")
    Call<ResponseBody> postRegistrationDetails(@Body Register register);

    @POST("/contest/registerUser/{contestId}/{userId}/{contestType}")
    Call<ResponseBody> postSubscribed(@Path("contestId") String contestId,@Path("userId") String userId,@Path("contestType") String contestType);

    @POST("/jwt/getUserDetails")
    Call<LoginResponse> getUserDetails(@Header("Authorization") String token, @Body Provider provider);

    @POST("/role/updateRole")
    Call<RoleResponse> updateRole(@Header("Authorization") String token, @Body Role role);

    @GET("/ads/getAds/3")//172.16.20.181:8080
    Call<List<AdResponse>> getAds(@Header("Authorization") String token);

    @POST("/search/saveQuizData")
    Call<ResponseBody> analytics(@Body Analytics analytics);

}
