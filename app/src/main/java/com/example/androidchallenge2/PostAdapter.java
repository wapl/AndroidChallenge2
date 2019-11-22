package com.example.androidchallenge2;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {

    private ArrayList<PostModel> postList;

    public PostAdapter()
    {
        postList=new ArrayList<PostModel>();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.post_row,parent,false);
        PostHolder postHolder=new PostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.setTitle(postList.get(position).getTitle());
        holder.setDate(  postList.get(position).getTimestamp()  );
        holder.setDesc(postList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
