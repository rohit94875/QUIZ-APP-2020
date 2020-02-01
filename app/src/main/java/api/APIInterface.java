package api;

import java.util.List;

import okhttp3.ResponseBody;
import com.coviam.quizapp.pojo.Category;
import com.coviam.quizapp.pojo.ContestList;
import com.coviam.quizapp.pojo.Login;
import com.coviam.quizapp.pojo.Profile;
import com.coviam.quizapp.pojo.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/contentservice/getCategories")
    Call<List<Category>> getCategories();

    @GET("/contest/getStaticContestsByCategory/{categoryId}")
    Call<List<ContestList>> getContests(@Path("categoryId") String categoryId);

    @GET("/contest/getDynmicContestByCategory/{categoryId}")
    Call<List<ContestList>> getDynamicContests(@Path("categoryId") String categoryId);


    @GET("")
    Call<Profile> getPoints();

    @POST("")
    Call<ResponseBody> login(@Body Login login);

    @POST("")
    Call<ResponseBody> postRegistrationDetails(@Body Register register);

    @POST("/contest/registerUser/{contestId}/{userId}/{contestType}")
    Call<ResponseBody> postSubscribed(@Path("contestId") String contestId,@Path("userId") String userId,@Path("contestType") String contestType);
}