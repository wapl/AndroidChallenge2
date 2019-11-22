package com.example.androidchallenge2;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostHolder extends RecyclerView.ViewHolder {
    private TextView Title;
    private TextView Date;
    private TextView Desc;
    private TextView Category;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    public PostHolder(@NonNull View itemView) {
        super(itemView);
        Title=itemView.findViewById(R.id.HoldTitle);
        Date=itemView.findViewById(R.id.HoldDate);
        Desc=itemView.findViewById(R.id.HoldDesc);
        Category=itemView.findViewById(R.id.HoldCategory);

    }
    public void setTitle(String Title)
    {
        this.Title.setText(Title);
    }
    public void setDate(Timestamp Date)
    {
        this.Date.setText(sdf.format(Date));
    }
    public void setDesc(String Desc)
    {
        this.Desc.setText(Desc);
    }
    public void setCategory(String Category)
    {
        this.Category.setText(Category);
    }


}
