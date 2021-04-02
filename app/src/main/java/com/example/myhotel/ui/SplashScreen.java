package com.example.myhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.myhotel.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
 private TextView intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intro=findViewById(R.id.intro_txt);
        StartAnimation();
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 3000); }



    private  void StartAnimation()
    {

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fadein);
        intro.startAnimation(animation);

    }
}