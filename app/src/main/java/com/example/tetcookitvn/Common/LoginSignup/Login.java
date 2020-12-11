package com.example.tetcookitvn.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.tetcookitvn.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);
    }

    public void logRegister(View view){
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);
        finish();
    }

    public void logLogin(View view){

    }

    public void logBack(View view){
        Intent intent = new Intent(this,RetailerStartUpScreen.class);
        startActivity(intent);
        finish();
    }
}