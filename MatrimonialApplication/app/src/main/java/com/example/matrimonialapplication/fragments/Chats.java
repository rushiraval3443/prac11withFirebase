package com.example.matrimonialapplication.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonialapplication.R;
import com.example.matrimonialapplication.RegisterActivity;
import com.example.matrimonialapplication.model.UserModel;
import com.example.matrimonialapplication.showChatUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Chats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Chats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chats.
     */
    // TODO: Rename and change types and number of parameters
    public static Chats newInstance(String param1, String param2) {
        Chats fragment = new Chats();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    List<UserModel> userModelList = new ArrayList<>();
    showChatUser adapter;
    RecyclerView rv_showRequest;

    UserModel userModel = null;

    Button btn_acceptRequest;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        rv_showRequest = view.findViewById(R.id.rv_showRequest);
        rv_showRequest.setHasFixedSize(true);
        rv_showRequest.setLayoutManager(new LinearLayoutManager(getContext()));

        loadUsers();

        return view;
    }

    private void loadRequests() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.REQUESTS).child(userModel.getUserName());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userModelList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserModel model = dataSnapshot.getValue(UserModel.class);
                    userModelList.add(model);
                }
                adapter = new showChatUser(getContext(), userModelList, userModel.getUserName());
                rv_showRequest.setAdapter(adapter);
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