package com.spaikdeveloper.firebasebitm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {
    private Context context;
    private ArrayList<User> userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.card_view,parent,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
       holder.detailsTv.setText("Name: "+userArrayList.get(position).getName()+"\nEmail: "+userArrayList.get(position).getEmail()+"\nAge: "+userArrayList.get(position).getAge()+"\nBirth Date: "+userArrayList.get(position).getBirthDate());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView detailsTv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            detailsTv =itemView.findViewById(R.id.detailsTV);
        }
    }
}
