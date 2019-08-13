package com.example.akashpatel.miniproject.UI;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akashpatel.miniproject.R;

public class SplashActivity extends AppCompatActivity {

    Animation animTogether,animMove;
    ImageView img;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Splash Activity");
        setContentView(R.layout.activity_splash);

        // Slide Up
        Handler abc=new Handler();
        img=(ImageView)findViewById(R.id.imgVwBal);
        txt=(TextView)findViewById(R.id.txtVwSplash);

        animTogether = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.together);
        img.startAnimation(animTogether);

        animMove = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        txt.startAnimation(animMove);

        abc.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,CategoryActivity.class);
                startActivity(intent);

                finish();
            }
        },7000);

    }
}
