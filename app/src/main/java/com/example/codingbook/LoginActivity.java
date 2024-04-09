package com.example.codingbook;

import static com.example.codingbook.Utlis.isValidEmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.codingbook.databinding.ActivityLoginBinding;
import com.example.codingbook.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;


    private FirebaseAuth Auth;
    private FirebaseDatabase database;

    private static  final  int RC_SIGN_IN=123;
    private GoogleSignInClient gsc;

    private ProgressDialog progressDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Auth =FirebaseAuth.getInstance();
        if(Auth.getCurrentUser() != null)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        binding.signcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    binding.passwordedittext.setTransformationMethod(null);
                }
                else
                {
                    binding.passwordedittext.setTransformationMethod(new PasswordTransformationMethod());
                }
                binding.passwordedittext.setSelection(binding.passwordedittext.getText().length());
            }
        });
        binding.registerTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateNewAccount.class));
            }
        });
        database = FirebaseDatabase.getInstance();


        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!= null)
        {
            nagivateToSecondActivity();
        }

        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_Method();
            }
        });

    }
    private void signIn(){
        showProgressBar();
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                hideProgressBar();
                task.getResult(ApiException.class);
                nagivateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Sorry cannot login with your google account", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void nagivateToSecondActivity()
    {
        hideProgressBar();
        finish();
        Intent intent =  new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.INVISIBLE);
    }
    private void login_Method()
    {   progressDailog =  new ProgressDialog(this);
        progressDailog.setTitle("Singing-in  please wait....");
        String email = binding.usernameedittext.getText().toString();
        String password = binding.passwordedittext.getText().toString();
        progressDailog.show();
        if(email.isEmpty())
        {
            Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(email)) {
            Toast.makeText(getApplicationContext(),"Invalid email_id ",Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(),"please enter password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    progressDailog.dismiss();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDailog.dismiss();
                    Toast.makeText(LoginActivity.this, "Sorry Check your email or password", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}