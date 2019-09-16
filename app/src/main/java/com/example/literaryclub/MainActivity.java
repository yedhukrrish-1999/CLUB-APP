package com.example.literaryclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView bgapp, clover;
    private LinearLayout textsplash, texthome, menus;
    private Animation frombottom;
    private ImageButton admin;
    private ImageButton student;
    private ImageView des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        admin=(ImageButton) findViewById(R.id.adminbutton);
        student=(ImageButton)findViewById(R.id.studentbutton);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MainActivity.this,returnactivity.class);
                startActivity(intent3);

            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(MainActivity.this,returnactivity.class);
                startActivity(intent4);

            }
        });
        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        des=(ImageView)findViewById(R.id.design);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        menus = (LinearLayout) findViewById(R.id.menus);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        bgapp.animate().translationY(-1900).setDuration(1000).setStartDelay(600);
        clover.animate().alpha(0).setDuration(1000).setStartDelay(900);
        textsplash.animate().translationY(140).alpha(0).setDuration(1000).setStartDelay(600);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);



    }
}
