package com.example.literaryclub;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class returnactivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btRegister;
    private TextView tvLogin;
    private EditText mail;
    private EditText passs;
    private FirebaseAuth firebaseAuth;
    private Button login;
    private ProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnactivity);
        btRegister  = findViewById(R.id.btRegister);
        tvLogin     = findViewById(R.id.tvLogin);
        mail=(EditText)findViewById(R.id.emailtext);
        passs=(EditText)findViewById(R.id.passwordtext);
        login=(Button)findViewById(R.id.loginbutton);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        if(firebaseAuth.getCurrentUser()!= null){
            Intent intentret=new Intent(returnactivity.this,Profilepage.class);
            startActivity(intentret);

        }


    }

    @Override
    public void onClick(View view) {
        if (view==btRegister){
            Intent intent   = new Intent(returnactivity.this,RegisterActivity.class);
            Pair[] pairs    = new Pair[1];
            pairs[0] = new Pair<View,String>(tvLogin,"tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(returnactivity.this,pairs);
            startActivity(intent,activityOptions.toBundle());
        }
        if(view==login){
            userlogin();
        }

    }

    private void userlogin() {
        String email1=mail.getText().toString().trim();
        String passs1=passs.getText().toString().trim();
        if(TextUtils.isEmpty(email1)){
            Toast.makeText(this,"enter the text correctly",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(passs1)){
            Toast.makeText(this,"enter the text correctly",Toast.LENGTH_SHORT).show();
            return;

        }
        progressDialog.setMessage("login on process");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email1,passs1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(returnactivity.this,"logged in",Toast.LENGTH_SHORT).show();
                            Intent intentret=new Intent(returnactivity.this,Profilepage.class);
                            startActivity(intentret);


                        }

                    }
                });

    }
}
