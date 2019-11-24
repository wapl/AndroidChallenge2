package com.example.androidchallenge2;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {
    private ArrayList<PostModel> fullPostList;
    private ArrayList<PostModel> postList;

    public PostAdapter()
    {

        postList=new ArrayList<PostModel>();
        fullPostList=new ArrayList<PostModel>();

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
        holder.setDate(postList.get(position).getTimestamp());

        holder.setDesc(postList.get(position).getDescription());
        holder.setCategory(postList.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void deleteItem(int position)
    {
        DatabaseReference datbasePost= FirebaseDatabase.getInstance().getReference("Posts").child(postList.get(position).getId());
        datbasePost.removeValue();
        postList.remove(position);
        fullPostList.remove(position);
        notifyItemRemoved(position);
    }
    public void addItem(PostModel postModel)
    {

            boolean exists=false;
            int i=0;
            while(!exists && i<postList.size())
            {
                if(postList.get(i).getId()==postModel.getId())
                {
                    exists=true;
                }
                i++;
            }
            if(exists==false)
            {
                postList.add(postModel);
                fullPostList.add(postModel);
                notifyItemInserted(postList.size() - 1);
            }

    }

    public void Filter(String SelectedItem)
    {

        if(SelectedItem.equals("Creation Date"))
        {
            ArrayList <PostModel> temp= fullPostList.clone();
            int i;
            for( i=0;i<temp.size();i++)
            {
                postList.add(temp.get(i));


            }
        }
        else
        {
            int size=postList.size();
           postList.clear();
           notifyItemRangeRemoved(0,size);
           int i;

            for( i=0;i<fullPostList.size();i++)
            {
                if(fullPostList.get(i).getCategory().equals(SelectedItem))
                {
                    postList.add(fullPostList.get(i));

                }
            }
           notifyItemRangeInserted(0,postList.size());

        }

    }










}
