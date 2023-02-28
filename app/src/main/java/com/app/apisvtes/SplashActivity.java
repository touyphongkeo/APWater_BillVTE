package com.app.apisvtes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {


    public static int splashTimeOut = 3000;
    Animation fromtopbottom, smtobig;
    ImageView imageview;
    TextView textView;

    ProgressBar progressBarStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageview = findViewById(R.id.imageview);
        textView = findViewById(R.id.textView);
        progressBarStyle = findViewById(R.id.progressBarStyl);


        fromtopbottom = AnimationUtils.loadAnimation(this, R.anim.fromtopbottom);
        smtobig = AnimationUtils.loadAnimation(this, R.anim.smtobig);


        imageview.startAnimation(fromtopbottom);
        textView.startAnimation(fromtopbottom);
        progressBarStyle.startAnimation(fromtopbottom);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, splashTimeOut);
    }
}

