package co.com.ceiba.mobile.pruebadeingreso.services;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.Posts;
import co.com.ceiba.mobile.pruebadeingreso.models.Users;
import co.com.ceiba.mobile.pruebadeingreso.services.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolder {

    @GET(Endpoints.GET_USERS)
    Call<List<Users>> getUsers();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Posts>> getPostsUser(@Query("userId") Integer id);
}
