package co.com.test.mobile.retrofit.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.com.test.mobile.retrofit.models.Users;
import co.com.test.mobile.retrofit.services.rest.Endpoints;
import co.com.test.mobile.retrofit.services.JsonPlaceHolder;
import co.com.test.mobile.retrofit.utilities.Resource;
import co.com.test.mobile.retrofit.utilities.SQLiteConnectionHelper;
import co.com.test.mobile.retrofit.utilities.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Resource<List<Users>>> usersList;
    private SQLiteConnectionHelper conn;

    public MainActivityViewModel(@NonNull Application application){
        super(application);
        usersList = new MutableLiveData<>();
    }

    private boolean existsUsers() {
        conn = new SQLiteConnectionHelper(getApplication(), Utilities.DB, null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] columns = {Utilities.USERS_ID};
        @SuppressLint("Recycle") Cursor cursor = db.query(Utilities.USERS,columns,null,null,null,null,null,"1");
        boolean resp = cursor.moveToFirst();
        db.close();
        return resp;
    }

    private void importUsers() {
        conn = new SQLiteConnectionHelper(getApplication(), "androidDB", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        usersList.postValue(Resource.loading());

        JsonPlaceHolder jsonPlaceHolder;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Users>> call = jsonPlaceHolder.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()){
                    // write users in db
                    for (Users user : response.body()){
                        saveUser(user, db);
                    }
                    // saveUserOnLive(response.body());
                    usersList.postValue(Resource.success(response.body()));
                } else {
                    Log.i("onResponseNoOk", String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.i("onFailure",t.getMessage());
            }
        });

    }

    private void saveUser(Users user, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(Utilities.USERS_NAME, user.getName());
        values.put(Utilities.USERS_USERNAME, user.getUsername());
        values.put(Utilities.USERS_EMAIL, user.getEmail());
        values.put(Utilities.USERS_SUITE, user.getAddress().getSuite());
        values.put(Utilities.USERS_STREET, user.getAddress().getStreet());
        values.put(Utilities.USERS_CITY, user.getAddress().getCity());
        values.put(Utilities.USERS_ZIPCODE, user.getAddress().getZipcode());
        values.put(Utilities.USERS_GEO_LAT, user.getAddress().getGeo().getLat());
        values.put(Utilities.USERS_GEO_LNG, user.getAddress().getGeo().getLng());
        values.put(Utilities.USERS_PHONE, user.getPhone());
        values.put(Utilities.USERS_WEBSITE, user.getWebsite());
        values.put(Utilities.USERS_COMPANY, user.getCompany().getName());
        values.put(Utilities.USERS_CATCH_PHRASE, user.getCompany().getCatchPhrase());
        values.put(Utilities.USERS_BS, user.getCompany().getBs());
        db.insert(Utilities.USERS, Utilities.USERS_ID, values);
    }

    public void setUsers(){
        List<Users> usersListLocal = new ArrayList<>();
        conn = new SQLiteConnectionHelper(getApplication(), Utilities.DB,null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] columns = {Utilities.USERS_ID, Utilities.USERS_NAME, Utilities.USERS_PHONE, Utilities.USERS_EMAIL};
        Cursor cursor = db.query(Utilities.USERS,columns,null,null,null,null,null);
        try{
            while (cursor.moveToNext()){
                usersListLocal.add(new Users(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }
        } finally {
            cursor.close();
        }
        usersList.setValue(Resource.success(usersListLocal));
    }

    public MutableLiveData<Resource<List<Users>>> getUsers(){
        // if is first time
        if (!existsUsers()){
            importUsers();
        } else {
            setUsers();
        }

        return usersList;
    }

}
