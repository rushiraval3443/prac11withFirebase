package com.example.matrimonialapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.matrimonialapplication.R;
import com.example.matrimonialapplication.RegisterActivity;
import com.example.matrimonialapplication.model.ChatModel;
import com.example.matrimonialapplication.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<ChatModel> mChat;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    public ChatAdapter(Context mContext, List<ChatModel> mChat) {
        this.mContext = mContext;
        this.mChat = mChat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel chat = mChat.get(position);
        holder.tv_showMessage.setText(chat.getMessage());

        reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS).child(chat.getSender());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel user = snapshot.getValue(UserModel.class);
                String imageUrl = "";
                if (user != null) {
                    imageUrl = user.getUserImage();

                } else {
                    Log.d("TAG", "onDataChange: " + user);
                }
                try {
                    if (imageUrl.equals("default")) {
                        holder.cv_displayImage.setImageResource(R.drawable.user);
                    } else {
                        Glide.with(mContext).load(imageUrl).into(holder.cv_displayImage);
                    }
                } catch (Exception e) {
                    Log.d("TAG", "onDataChange: ");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_showMessage;
        private CircleImageView cv_displayImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_showMessage = itemView.findViewById(R.id.tv_showMessage);
            cv_displayImage = itemView.findViewById(R.id.cv_profile_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
