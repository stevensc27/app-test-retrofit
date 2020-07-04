package co.com.ceiba.mobile.pruebadeingreso.ui.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;;
import co.com.ceiba.mobile.pruebadeingreso.models.Users;
import co.com.ceiba.mobile.pruebadeingreso.ui.viewmodel.MainActivityViewModel;
import co.com.ceiba.mobile.pruebadeingreso.models.UserVo;
import co.com.ceiba.mobile.pruebadeingreso.ui.adapter.MainActivityAdapter;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private List<Users> usersArrayList = new ArrayList<>();
    private MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // search field
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editTextSearch.getText().toString().toLowerCase();
                ArrayList<Users> newUsersList = new ArrayList<>();

                for (int i=0;i<usersArrayList.size();i++){
                    Users user = usersArrayList.get(i);
                    if (user.getName().toLowerCase().contains(input)){
                        newUsersList.add(user);
                    }
                }

                if (newUsersList.size() == 0){
                    adapter.setData(usersArrayList);
                }else {
                    adapter.setData(newUsersList);
                }
            }
        });
        progressBar = findViewById(R.id.progressBarUsers);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSearchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        mainActivityViewModel.getUsers().observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show();
                    break;
                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    usersArrayList = response.data;
                    adapter = new MainActivityAdapter(response.data);
                    recyclerView.setAdapter(adapter);
                    break;
            }


        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}