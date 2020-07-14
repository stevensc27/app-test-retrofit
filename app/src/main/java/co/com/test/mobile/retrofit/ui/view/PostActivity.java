package co.com.test.mobile.retrofit.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.com.test.mobile.retrofit.R;
import co.com.test.mobile.retrofit.ui.adapter.PostActivityAdapter;
import co.com.test.mobile.retrofit.ui.viewmodel.PostActivityViewModel;

public class PostActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private PostActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Integer userId = getIntent().getIntExtra("id", 0);

        TextView name = findViewById(R.id.name);
        TextView phone = findViewById(R.id.phone);
        TextView email = findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBarPosts);

        PostActivityViewModel postActivityViewModel = new ViewModelProvider(this).get(PostActivityViewModel.class);
        postActivityViewModel.searchUser(userId);
        postActivityViewModel.getUser().observe(this, userVo -> {
            name.setText(userVo.getName());
            phone.setText(userVo.getPhone());
            email.setText(userVo.getEmail());
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPostsResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        postActivityViewModel.getPosts(userId).observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show();
                    break;
                case SUCCESS:
                    if (response.data != null) {
                        progressBar.setVisibility(View.GONE);
                        adapter = new PostActivityAdapter(response.data);
                        recyclerView.setAdapter(adapter);
                    }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}