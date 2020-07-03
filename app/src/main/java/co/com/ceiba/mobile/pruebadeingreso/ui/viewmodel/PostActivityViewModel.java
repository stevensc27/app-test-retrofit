package co.com.ceiba.mobile.pruebadeingreso.ui.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.Posts;
import co.com.ceiba.mobile.pruebadeingreso.models.UserVo;
import co.com.ceiba.mobile.pruebadeingreso.services.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.services.JsonPlaceHolder;
import co.com.ceiba.mobile.pruebadeingreso.utilities.SQLiteConnectionHelper;
import co.com.ceiba.mobile.pruebadeingreso.utilities.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivityViewModel extends AndroidViewModel {

    private MutableLiveData<UserVo> user;
    private MutableLiveData<List<Posts>> postsList;
    private SQLiteConnectionHelper conn;

    public PostActivityViewModel(@NonNull Application application) {
        super(application);

        user = new MutableLiveData<>();
        postsList = new MutableLiveData<>();
        conn = new SQLiteConnectionHelper(getApplication(), Utilities.DB, null, 1);
    }

    public void searchUser(Integer id){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] columns  = {Utilities.USERS_ID, Utilities.USERS_NAME, Utilities.USERS_PHONE, Utilities.USERS_EMAIL};
        String[] args     = {String.valueOf(id)};
        @SuppressLint("Recycle") Cursor cursor = db.query(Utilities.USERS,columns,Utilities.USERS_ID+"=?",args,null,null,null,"1");
        try{
            if (cursor.moveToFirst()) {
                UserVo userVo = new UserVo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                user.setValue(userVo);
            }
        } finally {
            cursor.close();
        }
    }

    public LiveData<UserVo> getUser(){
        return user;
    }


    public MutableLiveData<List<Posts>> getPosts(Integer id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Posts>> call = jsonPlaceHolder.getPostsUser(id);
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.isSuccessful()){
                    postsList.postValue(response.body());
                } else {
                    Log.i("onResponseNoOk", String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.i("onFailure",t.getMessage());
            }
        });
        return postsList;


    }


}
