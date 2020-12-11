package com.example.tetcookitvn.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tetcookitvn.R;
import com.example.tetcookitvn.User.UserDashboard;

public class RetailerStartUpScreen extends AppCompatActivity {

    ImageView imvRetailerBanner;
    TextView tvRetailerLogo, tvRetailerSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_start_up_screen);

        imvRetailerBanner = findViewById(R.id.imvRetailerBanner);
        tvRetailerLogo = findViewById(R.id.tvRetailerLogo);
        tvRetailerSlogan= findViewById(R.id.tvRetailerSlogan);
    }

    public void login(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void signup(View view){

        startActivity(new Intent(getApplicationContext(),Signup.class));
    }

    public void back(View view){
        Intent intent = new Intent(RetailerStartUpScreen.this,UserDashboard.class);

        Pair[]pairs = new Pair[3];
        pairs[0] = new Pair<View,String>(imvRetailerBanner,"banner_trans");
        pairs[1] = new Pair<View,String>(tvRetailerLogo,"logo_trans");
        pairs[2] = new Pair<View,String>(tvRetailerSlogan,"slogan_trans");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this,pairs);
            startActivity(intent,options.toBundle());
            finish();
        }
        else {
            startActivity(intent);
        }
    }
}