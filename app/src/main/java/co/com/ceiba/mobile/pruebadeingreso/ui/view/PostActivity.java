package co.com.ceiba.mobile.pruebadeingreso.ui.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.ui.viewmodel.PostActivityViewModel;
import co.com.ceiba.mobile.pruebadeingreso.ui.adapter.PostActivityAdapter;

public class PostActivity extends AppCompatActivity {

    private PostActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Integer userId = getIntent().getIntExtra("id",0);

        TextView name = findViewById(R.id.name);
        TextView phone = findViewById(R.id.phone);
        TextView email = findViewById(R.id.email);

        PostActivityViewModel postActivityViewModel = new ViewModelProvider(this).get(PostActivityViewModel.class);
        postActivityViewModel.searchUser(userId);
        postActivityViewModel.getUser().observe(this, userVo -> {
            name.setText(userVo.getName());
            phone.setText(userVo.getPhone());
            email.setText(userVo.getEmail());
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPostsResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        postActivityViewModel.getPosts(userId).observe(this, posts -> {
            adapter = new PostActivityAdapter(posts);
            recyclerView.setAdapter(adapter);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
