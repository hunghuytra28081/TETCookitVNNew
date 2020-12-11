package com.example.tetcookitvn.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tetcookitvn.HelperClasses.SliderAdapter;
import com.example.tetcookitvn.R;
import com.example.tetcookitvn.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView dost[];

    Button btnGetStarted;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        btnGetStarted = findViewById(R.id.btnGetStarted);

        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDost(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void  skip(View view){
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }

    public void start(View view){
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }

    public void  next(View view){
        viewPager.setCurrentItem(currentPos+1);
    }

    private void addDost(int position){

        dost = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i<dost.length; i++){
            dost[i] = new TextView(this);
            dost[i].setText(Html.fromHtml("&#8226;"));
            dost[i].setTextSize(35);

            dotsLayout.addView(dost[i]);
        }
        if (dost.length>0){
            dost[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDost(position);
            currentPos = position;
            if (position == 0){
                btnGetStarted.setVisibility(View.INVISIBLE);
            }
            else if (position == 1){
                btnGetStarted.setVisibility(View.INVISIBLE);
            }
            else if (position == 2){
                btnGetStarted.setVisibility(View.INVISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.right_animation);
                btnGetStarted.setAnimation(animation);
                btnGetStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}