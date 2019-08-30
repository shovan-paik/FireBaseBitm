package com.spaikdeveloper.firebasebitm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spaikdeveloper.firebasebitm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ActivityMainBinding binding;
    public static ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("User Registration");
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        init();
        getAllUsers();


        binding.saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nameET.getText().toString().trim();
                String email = binding.emailET.getText().toString().trim();
                String age = binding.ageET.getText().toString().trim();
                String birthDate = binding.birthDateET.getText().toString().trim();

                if (name.isEmpty()){
                    binding.nameET.setError("Input name");
                    binding.nameET.requestFocus();
                    return;
                }

                if (email.isEmpty()){
                    binding.emailET.setError("Input email");
                    binding.emailET.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.emailET.setError("Input an valid email");
                    binding.emailET.requestFocus();
                    return;
                }


                if (age.isEmpty()){
                    binding.ageET.setError("Input age");
                    binding.ageET.requestFocus();
                    return;
                }

                if (birthDate.isEmpty()){
                    binding.birthDateET.setError("Input birth date");
                    binding.birthDateET.requestFocus();
                    return;
                }

                User user = new User(name,email,Integer.parseInt(age),birthDate);

                DatabaseReference userRef = databaseReference.child("FireBaseBitm").child("users");
                userRef.push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Data input Successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        binding.detailUsersBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DetailsActivity.class));
            }
        });

    }

    private void getAllUsers() {
        DatabaseReference userRef = databaseReference.child("FireBaseBitm").child("users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    userList.clear();
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        User user = data.getValue(User.class);
                        userList.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void init() {
        userList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
}
