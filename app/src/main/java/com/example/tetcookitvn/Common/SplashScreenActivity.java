package com.example.tetcookitvn.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tetcookitvn.R;
import com.example.tetcookitvn.User.UserDashboard;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2700;

    Animation topAnim,bottomAnim;
    ImageView imvBanner;
    TextView tvLogo,tvSlogan;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        imvBanner = findViewById(R.id.imv_banner);
        tvLogo = findViewById(R.id.tv_logo);
        tvSlogan = findViewById(R.id.tv_slogan);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imvBanner.setAnimation(topAnim);
        tvLogo.setAnimation(bottomAnim);
        tvSlogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

                Boolean isFirstTime = onBoardingScreen.getBoolean("fisrtTime",true);

                if (isFirstTime){
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("fisrtTime",false);
                    editor.commit();

                    Intent intent = new Intent(SplashScreenActivity.this, OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreenActivity.this, UserDashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_SCREEN);
    }
}