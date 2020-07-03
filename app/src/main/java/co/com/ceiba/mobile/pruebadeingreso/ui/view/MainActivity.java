package co.com.ceiba.mobile.pruebadeingreso.ui.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;;
import co.com.ceiba.mobile.pruebadeingreso.ui.viewmodel.MainActivityViewModel;
import co.com.ceiba.mobile.pruebadeingreso.models.UserVo;
import co.com.ceiba.mobile.pruebadeingreso.ui.adapter.MainActivityAdapter;

public class MainActivity extends AppCompatActivity {

    private ArrayList<UserVo> usersArrayList = new ArrayList<>();
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
                ArrayList<UserVo> newUsersList = new ArrayList<>();

                for (int i=0;i<usersArrayList.size();i++){
                    UserVo userVo = usersArrayList.get(i);
                    if (userVo.getName().toLowerCase().contains(input)){
                        newUsersList.add(userVo);
                    }
                }

                if (newUsersList.size() == 0){
                    adapter.setData(usersArrayList);
                }else {
                    adapter.setData(newUsersList);
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSearchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        mainActivityViewModel.getUsersList().observe(this, userVos -> {
            usersArrayList = userVos;
            adapter = new MainActivityAdapter(userVos);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}