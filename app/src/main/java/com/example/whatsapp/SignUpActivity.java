package com.example.whatsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.whatsapp.Models.Users;
import com.example.whatsapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
private FirebaseAuth auth;
FirebaseDatabase database;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are Creating an account");

        binding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.username.getText().toString().isEmpty()){
                    binding.username.setError("Enter username");
                    return;
                }
                else if(binding.edEmail.getText().toString().isEmpty()){
                    binding.edEmail.setError("Enter email");
                    return;
                }
                else if(binding.etpassword.getText().toString().isEmpty()){
                    binding.etpassword.setError("Enter password");
                    return;
                }

                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.edEmail.getText().toString(),binding.etpassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Users users=new Users(binding.username.getText().toString(),binding.etpassword.getText().toString(),binding.edEmail.getText().toString());
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(users);
                            Toast.makeText(SignUpActivity.this, "User Created Successful", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        binding.tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}