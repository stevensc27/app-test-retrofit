package co.com.test.mobile.retrofit.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.test.mobile.retrofit.R;
import co.com.test.mobile.retrofit.models.Posts;

public class PostActivityAdapter extends RecyclerView.Adapter<PostActivityAdapter.ViewHolderPostActivity> {
    @NonNull
    private List<Posts> postsList;

    public PostActivityAdapter(@NonNull List<Posts> postsList) {
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostActivityAdapter.ViewHolderPostActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item,parent,false);
        return new ViewHolderPostActivity(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPostActivity holder, int position) {
        holder.title.setText(postsList.get(position).getTitle());
        holder.body.setText(postsList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolderPostActivity extends RecyclerView.ViewHolder {

        TextView title, body;

        public ViewHolderPostActivity(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }

    public void setData(List<Posts> postsList){
        this.postsList = postsList;
        notifyDataSetChanged();
    }
}
