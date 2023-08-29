package com.example.matrimonialapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.matrimonialapplication.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class showUserAdapter extends RecyclerView.Adapter<showUserAdapter.viewholder> {

    private Context context;
    private List<UserModel> incomeModelList = new ArrayList<>();
    private String username;

    public showUserAdapter(Context context, List<UserModel> incomeModelList, String username) {
        this.context = context;
        this.incomeModelList = incomeModelList;
        this.username = username;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_user, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        UserModel model = incomeModelList.get(position);

        holder.tv_userName.setText(model.getUserName());
        if (model.getUserAge().equals("")) {
            holder.tv_userAge.setText("");
        } else {
            holder.tv_userAge.setText(model.getUserAge());
        }
        if (model.getUserAddress().equals("")) {
            holder.tv_userAddress.setText("");
        } else {
            holder.tv_userAddress.setText(model.getUserAddress());
        }
        if (model.getUserHeight().equals("")) {
            holder.tv_userHeight.setText("");
        } else {
            holder.tv_userHeight.setText(model.getUserHeight());
        }
        if (model.getUserAddress().equals("")) {
            holder.tv_userAddress.setText("");
        } else {
            holder.tv_userAddress.setText(model.getUserAddress());
        }
        if (model.getUserReligion().equals("")) {
            holder.tv_userReligion.setText("");
        } else {
            holder.tv_userReligion.setText(model.getUserReligion());
        }
        if (model.getUserImage().equals(RegisterActivity.DEFAULT_IMAGE)) {
            holder.iv_userImage.setImageResource(R.drawable.user);
        } else {
            Glide.with(context).load(model.getUserImage()).into(holder.iv_userImage);
        }

        holder.btn_sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequest(model);
                holder.btn_sendRequest.setText("Request Sent");
            }
        });

    }

    private void sendRequest(UserModel model) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Sending Request");
        dialog.show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.REQUESTS).child(model.getUserName());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", model.getUserId());
        hashMap.put("username", model.getUserName());
        hashMap.put("sender", username);

        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(context, "Request Sent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return incomeModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_userName, tv_userAge, tv_userHeight, tv_userAddress, tv_userReligion;
        ImageView iv_userImage;
        Button btn_sendRequest;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_userAge = itemView.findViewById(R.id.tv_userAge);
            tv_userHeight = itemView.findViewById(R.id.tv_userHeight);
            tv_userAddress = itemView.findViewById(R.id.tv_userAddress);
            tv_userReligion = itemView.findViewById(R.id.tv_userReligion);
            iv_userImage = itemView.findViewById(R.id.iv_userImage);
            btn_sendRequest = itemView.findViewById(R.id.btn_sendRequest);

        }
    }
}
