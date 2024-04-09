package com.example.codingbook;

import static com.example.codingbook.CheckPassword.isStrongPassword;
import static com.example.codingbook.Utlis.isValidEmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codingbook.databinding.ActivityCreateNewAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateNewAccount extends AppCompatActivity {

    ActivityCreateNewAccountBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore  firestore;

    ProgressDialog progressDialog;
    private String user_name,user_email,user_password,user_repeatpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNewAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth =  FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            // Toggle between PasswordTransformationMethod and null based on checkbox state
            if (isChecked) {
                binding.registerPasswordeditTextview.setTransformationMethod(null);
                binding.registerRepeatPwEditextview.setTransformationMethod(null);

            } else {
                binding.registerPasswordeditTextview.setTransformationMethod(new PasswordTransformationMethod());
                binding.registerRepeatPwEditextview.setTransformationMethod(new PasswordTransformationMethod());
            }
            // Set the cursor position to the end after changing the TransformationMethod
            binding.registerPasswordeditTextview.setSelection(binding.registerPasswordeditTextview.getText().length());
        });
        binding.registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   create_account();

            }
        });

        binding.singintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CreateNewAccount.this,LoginActivity.class));
                //finish();
            }
        });
    }

    private void create_account()
    {
         user_name = binding.registerNameEditextview.getText().toString();
         user_email = binding.regiterEmaileditTextview.getText().toString();
         user_password = binding.registerPasswordeditTextview.getText().toString();
         user_repeatpw =  binding.registerRepeatPwEditextview.getText().toString();

         if(user_name.isEmpty())
         {
             Toast.makeText(getApplicationContext(),"Enter your name",Toast.LENGTH_SHORT).show();
         } else if (user_email.isEmpty()) {
             Toast.makeText(getApplicationContext(),"Enter your email",Toast.LENGTH_SHORT).show();
         } else if (!isValidEmail(user_email)) {
             Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
         } else if (user_password.isEmpty()) {
             Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_SHORT).show();
         } else if (user_repeatpw.isEmpty()) {
             Toast.makeText(getApplicationContext(),"Enter your confirm password",Toast.LENGTH_SHORT).show();
         } else if (!isStrongPassword(user_password)) {
             Toast.makeText(getApplicationContext(),"Weak password",Toast.LENGTH_SHORT).show();
         }
         else if (!user_password.equals(user_repeatpw)) {
             Toast.makeText(this, "Passwords do not matched!", Toast.LENGTH_SHORT).show();
         }
         else {
             progressDialog =  new ProgressDialog(this);
             progressDialog.setTitle("Creating new account...");

              final User  newuser = new User(user_name,user_email,user_password,user_repeatpw);
              progressDialog.show();
              auth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {
                         String userID = task.getResult().getUser().getUid();// to store user id
                         firestore.collection("bookuserdb")
                                 .document(userID)
                                 .set(newuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if(task.isSuccessful())
                                         {
                                             progressDialog.dismiss();
                                             startActivity(new Intent(CreateNewAccount.this,MainActivity.class));
                                             finish();
                                         }
                                         else {
                                             Toast.makeText(CreateNewAccount.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                         }
                                     }
                                 });
                        //Toast.makeText(CreateNewAccount.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                     }
                     else {
                         progressDialog.dismiss();
                         Toast.makeText(CreateNewAccount.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                     }
                  }
              });
         }



    }
}