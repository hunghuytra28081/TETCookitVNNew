package com.example.tetcookitvn.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tetcookitvn.Common.LoginSignup.RetailerStartUpScreen;
import com.example.tetcookitvn.R;
import com.google.android.material.navigation.NavigationView;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    Animation smallTopAnim, leftAnim, left2Anim, rightAnim;
    ImageView imvBannerScreen, imvMenuIcon, imvMenuCreate;
    TextView tvLogoScreen, tvSloganScreen;

    ConstraintLayout contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //------------------Hooks----------------
        imvBannerScreen = findViewById(R.id.imvBannerScreen);
        tvLogoScreen = findViewById(R.id.tvLogoScreen);
        tvSloganScreen = findViewById(R.id.tvSloganScreen);
        imvMenuIcon = findViewById(R.id.menu_icon);
        imvMenuCreate = findViewById(R.id.menu_create);
        contentView = findViewById(R.id.contentView);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Anim
        smallTopAnim = AnimationUtils.loadAnimation(this, R.anim.small_top_animation);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        left2Anim = AnimationUtils.loadAnimation(this, R.anim.left2_animation);
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.right_animation);

        imvBannerScreen.setAnimation(rightAnim);
        tvLogoScreen.setAnimation(leftAnim);
        tvSloganScreen.setAnimation(left2Anim);
        imvMenuCreate.setAnimation(smallTopAnim);

        //Navigation Drawer
        navigationDrawer();
    }

    //Navigation Drawer Functions
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        imvMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    public void callRetailerScreens(View view){
        Intent intent = new Intent(UserDashboard.this,RetailerStartUpScreen.class);

        Pair[]pairs = new Pair[3];
        pairs[0] = new Pair<View,String>(imvBannerScreen,"banner_trans");
        pairs[1] = new Pair<View,String>(tvLogoScreen,"logo_trans");
        pairs[2] = new Pair<View,String>(tvSloganScreen,"slogan_trans");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserDashboard.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}