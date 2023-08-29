package com.example.matrimonialapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonialapplication.adapter.ChatAdapter;
import com.example.matrimonialapplication.model.ChatModel;
import com.example.matrimonialapplication.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    CircleImageView cv_profileImage;
    TextView tv_username;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    Intent intent;

    ImageView iv_btnSend;
    ImageView iv_backBtn;
    EditText et_message;

    LinearLayout ll_usernameDetails;

    ChatAdapter messageAdapter;
    List<ChatModel> mChat;

    RecyclerView recyclerView;

    String groupName;

    UserModel userModel = null;

    boolean notify = false;
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        cv_profileImage = findViewById(R.id.cv_profile_image);
        tv_username = findViewById(R.id.tv_username);
        iv_btnSend = findViewById(R.id.iv_btnSend);
        et_message = findViewById(R.id.et_message);
        iv_backBtn = findViewById(R.id.iv_back);

        loadUsers();

        iv_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ll_usernameDetails = findViewById(R.id.ll_usernameDetails);

        recyclerView = findViewById(R.id.rv_chatMessages);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        intent = getIntent();
        groupName = "Nitika";

        tv_username.setText(groupName);

        iv_btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String msg = et_message.getText().toString();
                if (!msg.isEmpty()) {
                    sendMessage(firebaseUser.getUid(), msg, userModel.getUserName());
                } else {
                    et_message.setError("Empty Message");
                }
                et_message.setText("");
            }
        });

        readMessages(groupName);

    }

    private void sendMessage(String sender, String message, String groupName) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("message", message);

        reference.child(RegisterActivity.Chats).child(groupName).push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                }
            }
        });

    }

    private void readMessages(String groupName) {
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.Chats).child(groupName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatModel chat = dataSnapshot.getValue(ChatModel.class);

                    mChat.add(chat);
                    Log.d("TAG2", "message: " + chat.getMessage());
                    messageAdapter = new ChatAdapter(ChatActivity.this, mChat);
                    recyclerView.setAdapter(messageAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadUsers() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserModel model = dataSnapshot.getValue(UserModel.class);
                        if (!model.getUserId().equals(firebaseUser.getUid())) {
                        } else {
                            userModel = model;
                            Log.d("TAG1", "onDataChange: " + userModel.getUserName());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}