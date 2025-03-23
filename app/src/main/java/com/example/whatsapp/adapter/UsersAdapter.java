package com.example.whatsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.ChatDetailesActivity;
import com.example.whatsapp.Models.Users;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHoder> {

    ArrayList<Users> list;
    Context context;

    public UsersAdapter(ArrayList<Users> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {

        Users users=list.get(position);
        Picasso.get().load(users.getProfileic()).placeholder(R.drawable.profile).into(holder.image);
        holder.userName.setText(users.getUsername());

        FirebaseDatabase.getInstance().getReference().child("chats")
                        .child(FirebaseAuth.getInstance().getUid()+users.getUserId())
                        .orderByChild("timestamp")
                        .limitToLast(1)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.hasChildren()){
                                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                                holder.lastMessage.setText(snapshot1.child("message").getValue().toString());
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChatDetailesActivity.class);
                intent.putExtra("userId",users.getUserId());
                intent.putExtra("profilepic",users.getProfileic());
                intent.putExtra("userName",users.getUsername());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView userName,lastMessage;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.profile_image);
            userName=itemView.findViewById(R.id.userNameList);
            lastMessage=itemView.findViewById(R.id.lastMessage);

        }
    }
}
