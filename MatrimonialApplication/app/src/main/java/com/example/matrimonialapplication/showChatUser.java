package com.example.matrimonialapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.matrimonialapplication.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class showChatUser extends RecyclerView.Adapter<showChatUser.viewholder> {

    private Context context;
    private List<UserModel> incomeModelList = new ArrayList<>();
    private String username;

    public showChatUser(Context context, List<UserModel> incomeModelList, String username) {
        this.context = context;
        this.incomeModelList = incomeModelList;
        this.username = username;
    }

    @NonNull
    @Override
    public showChatUser.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_chat_user, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        UserModel model = incomeModelList.get(position);

        holder.tv_userName.setText(model.getUserName());
        Glide.with(context).load(model.getUserImage()).into(holder.iv_userImage);

    }

    @Override
    public int getItemCount() {
        return incomeModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_userName;
        Button btn_chat;
        ImageView iv_userImage;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_userName = itemView.findViewById(R.id.tv_userName);
            iv_userImage = itemView.findViewById(R.id.iv_userImage);
            btn_chat = itemView.findViewById(R.id.btn_chat);

        }
    }

}
