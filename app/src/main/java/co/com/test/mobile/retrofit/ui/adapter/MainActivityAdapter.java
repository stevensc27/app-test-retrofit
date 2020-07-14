package co.com.test.mobile.retrofit.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import co.com.test.mobile.retrofit.R;
import co.com.test.mobile.retrofit.models.Users;
import co.com.test.mobile.retrofit.ui.view.PostActivity;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolderMainActivity> {
    @NonNull
    private List<Users> usersList;

    public MainActivityAdapter(@NonNull List<Users> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MainActivityAdapter.ViewHolderMainActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        return new ViewHolderMainActivity(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMainActivity holder, int position) {
        holder.name.setText(usersList.get(position).getName());
        holder.phone.setText(usersList.get(position).getPhone());
        holder.email.setText(usersList.get(position).getEmail());

        holder.viewPost.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), PostActivity.class);
            intent.putExtra("id", usersList.get(position).getId());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolderMainActivity extends RecyclerView.ViewHolder {

        TextView name, phone, email;
        Button viewPost;
        
        public ViewHolderMainActivity(@NonNull View itemView) {
            super(itemView);
            
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            email = (TextView) itemView.findViewById(R.id.email);
            viewPost = (Button) itemView.findViewById(R.id.btn_view_post);
        }
    }

    public void setData(List<Users> usersList){
        this.usersList = usersList;
        notifyDataSetChanged();
    }
}
