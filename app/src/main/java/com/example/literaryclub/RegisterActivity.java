package com.example.literaryclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.literaryclub.R.id.rlayout;
import static com.example.literaryclub.R.id.usernametext;

public class RegisterActivity extends AppCompatActivity {
    private RelativeLayout rlayout;
    private Animation animation;
    private EditText mailid1,user2,pass2,pass3;
    private Button signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar =(Toolbar) findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        mailid1=(EditText)findViewById(R.id.emailtext1);
        user2=(EditText)findViewById(R.id.usernametext);
        pass2=(EditText)findViewById(R.id.passwordtext1);
        pass3=(EditText)findViewById(R.id.passwordtext2);
        signup=(Button)findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
            }

            private void registeruser() {
                String email=mailid1.getText().toString().trim();
                String password1=pass2.getText().toString().trim();
                String password2=pass3.getText().toString().trim();
                String user=user2.getText().toString().trim();
                if(password1.compareTo(password2)==1){
                    Toast.makeText(RegisterActivity.this, "type same password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this,"enter data correctly",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(RegisterActivity.this,"enter data correctly",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(password2)){
                    Toast.makeText(RegisterActivity.this,"enter data correctly",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(user)){
                    Toast.makeText(RegisterActivity.this,"enter data correctly",Toast.LENGTH_SHORT).show();
                    return;

                }
                progressDialog.setMessage("registration on process");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email,password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this,"Registration succesfull",Toast.LENGTH_SHORT).show();
                                    Intent intentreg=new Intent(RegisterActivity.this,returnactivity.class);
                                    startActivity(intentreg);
                                }
                                else {

                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this,"could not Register.try again later",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }

