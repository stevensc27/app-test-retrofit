package co.com.test.mobile.retrofit.services;

import java.util.List;

import co.com.test.mobile.retrofit.models.Posts;
import co.com.test.mobile.retrofit.models.Users;
import co.com.test.mobile.retrofit.services.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolder {

    @GET(Endpoints.GET_USERS)
    Call<List<Users>> getUsers();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Posts>> getPostsUser(@Query("userId") Integer id);
}
