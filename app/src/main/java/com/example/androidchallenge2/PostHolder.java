package com.example.androidchallenge2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostHolder extends RecyclerView.ViewHolder {
    private TextView Title;
    private TextView Date;
    private TextView Desc;

    public PostHolder(@NonNull View itemView) {
        super(itemView);
        Title=itemView.findViewById(R.id.HoldTitle);
        Date=itemView.findViewById(R.id.HoldDate);

    }
}
