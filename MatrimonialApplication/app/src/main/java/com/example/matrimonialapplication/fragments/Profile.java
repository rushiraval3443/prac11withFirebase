package com.example.matrimonialapplication.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.matrimonialapplication.R;
import com.example.matrimonialapplication.RegisterActivity;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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

    Button btn_addComplexion, btn_addEducation, btn_addFamily, btn_addHoroscope, btn_addAnnualIncome;

    FirebaseUser mAuth;
    UserModel userModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        init(view);
        loadUsers();

        btn_addComplexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComplexion();
            }
        });

        btn_addEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEducation();
            }
        });

        btn_addFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFamily();
            }
        });

        btn_addHoroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHoroscope();
            }
        });

        btn_addAnnualIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncome();
            }
        });

        return view;
    }

    private void addIncome() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final View customLayout = getLayoutInflater().inflate(R.layout.alert_add_income, null);
        EditText et_enterIncome = customLayout.findViewById(R.id.et_enterIncome);

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String income = et_enterIncome.getText().toString();

                if (income.isEmpty()) {
                    et_enterIncome.setError("Empty");
                    return;
                } else {

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Updating...");
                    dialog.show();

                    userModel.setIncome(income);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS).child(userModel.getUserName());
                    reference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Details Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alertDialog.dismiss();
                }

            }
        });
    }

    private void addHoroscope() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final View customLayout = getLayoutInflater().inflate(R.layout.alert_add_horoscope, null);
        EditText et_religion = customLayout.findViewById(R.id.et_religion);

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String religion = et_religion.getText().toString();

                if (religion.isEmpty()) {
                    et_religion.setError("Empty");
                    return;
                } else {

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Updating...");
                    dialog.show();

                    userModel.setUserReligion(religion);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS).child(userModel.getUserName());
                    reference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Details Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alertDialog.dismiss();
                }

            }
        });
    }

    private void addFamily() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final View customLayout = getLayoutInflater().inflate(R.layout.alert_add_family, null);
        EditText et_fathersName = customLayout.findViewById(R.id.et_fathersName);
        EditText et_fathersOccupation = customLayout.findViewById(R.id.et_fathersOccupation);
        EditText et_mothersName = customLayout.findViewById(R.id.et_mothersName);
        EditText et_mothersOccupation = customLayout.findViewById(R.id.et_mothersOccupation);
        EditText et_noOfSiblings = customLayout.findViewById(R.id.et_noOfSiblings);
        EditText et_address = customLayout.findViewById(R.id.et_address);

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fatherName = et_fathersName.getText().toString();
                String fatherOccupation = et_fathersOccupation.getText().toString();
                String motherName = et_mothersName.getText().toString();
                String motherOccupation = et_mothersOccupation.getText().toString();
                String noofsiblings = et_noOfSiblings.getText().toString();
                String address = et_address.getText().toString();

                if (fatherName.isEmpty()) {
                    et_fathersName.setError("Empty name");
                    return;
                } else if (fatherOccupation.isEmpty()) {
                    et_fathersOccupation.setError("Empty");
                    return;
                } else if (motherName.isEmpty()) {
                    et_mothersName.setError("Empty");
                    return;
                } else if (motherOccupation.isEmpty()) {
                    et_mothersOccupation.setError("Empty");
                    return;
                } else if (noofsiblings.isEmpty()) {
                    et_noOfSiblings.setError("Empty");
                    return;
                } else if (address.isEmpty()) {
                    et_address.setError("Empty");
                    return;
                } else {

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Updating...");
                    dialog.show();

                    userModel.setFatherName(fatherName);
                    userModel.setFatherOccupation(fatherOccupation);
                    userModel.setMotherName(motherName);
                    userModel.setMotherOccupation(motherOccupation);
                    userModel.setNoOfSiblings(noofsiblings);
                    userModel.setUserAddress(address);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS).child(userModel.getUserName());
                    reference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Details Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alertDialog.dismiss();
                }

            }
        });
    }

    private void addEducation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final View customLayout = getLayoutInflater().inflate(R.layout.alert_add_education, null);
        EditText et_latestEducation = customLayout.findViewById(R.id.et_latestEducation);
        EditText et_university = customLayout.findViewById(R.id.et_university);

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latestEducation = et_latestEducation.getText().toString();
                String university = et_university.getText().toString();

                if (latestEducation.isEmpty()) {
                    et_latestEducation.setError("Empty weight");
                    return;
                } else if (university.isEmpty()) {
                    et_university.setError("Empty Height");
                    return;
                } else {

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Updating...");
                    dialog.show();

                    userModel.setLatestEducation(latestEducation);
                    userModel.setUniversity(university);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS).child(userModel.getUserName());
                    reference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Details Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alertDialog.dismiss();
                }

            }
        });
    }

    private void addComplexion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final View customLayout = getLayoutInflater().inflate(R.layout.alert_add_complexion, null);
        EditText et_Weight = customLayout.findViewById(R.id.et_Weight);
        EditText et_userHeight = customLayout.findViewById(R.id.et_userHeight);
        EditText et_userDateOfBirth = customLayout.findViewById(R.id.et_userDateOfBirth);
        EditText et_complexion = customLayout.findViewById(R.id.et_complexion);

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = et_Weight.getText().toString();
                String height = et_userHeight.getText().toString();
                String dob = et_userDateOfBirth.getText().toString();
                String complexion = et_complexion.getText().toString();

                if (weight.isEmpty()) {
                    et_Weight.setError("Empty weight");
                    return;
                } else if (height.isEmpty()) {
                    et_userHeight.setError("Empty Height");
                    return;
                } else if (dob.isEmpty()) {
                    et_userDateOfBirth.setError("Empty Date of Birth");
                    return;
                } else {

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Updating...");
                    dialog.show();

                    userModel.setUserWeight(weight);
                    userModel.setUserHeight(height);
                    userModel.setUserDob(dob);
                    userModel.setUserAge("23 Yrs");
                    userModel.setUserComplexion(complexion);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.RIDER_USERS).child(userModel.getUserName());
                    reference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Details Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alertDialog.dismiss();
                }

            }
        });
    }

    private void init(View view) {
        btn_addComplexion = view.findViewById(R.id.btn_addComplexion);
        btn_addEducation = view.findViewById(R.id.btn_addEducation);
        btn_addFamily = view.findViewById(R.id.btn_addFamily);
        btn_addHoroscope = view.findViewById(R.id.btn_addReligion);
        btn_addAnnualIncome = view.findViewById(R.id.btn_addAnnualIncome);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
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