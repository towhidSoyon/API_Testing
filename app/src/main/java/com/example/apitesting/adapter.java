package com.example.apitesting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    private List<Post> list;

    public adapter(List<Post> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.MyViewHolder holder, int position) {

        holder.title.setText("ID: "+list.get(position).getUserId()+"\n"
        +"Post Id: "+list.get(position).getPostId()+"\n"
        +"Title: "+list.get(position).getTitle()+"\n"+"Text: "+list.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.singleTitleID);
        }
    }
}
