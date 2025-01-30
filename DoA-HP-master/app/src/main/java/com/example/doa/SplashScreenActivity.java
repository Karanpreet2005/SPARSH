package com.example.doa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ensure the app follows the system's light/dark mode settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Set the content view to the splash screen layout
        setContentView(R.layout.activity_splash_screen);

        // Find the views
        TextView nameTextView = findViewById(R.id.name);
        TextView nameTextView2 = findViewById(R.id.name2);
        ImageView logoImageView = findViewById(R.id.img_flavordb);

        // Load animations from res/anim folder
        Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);

        // Start the animations
        nameTextView.startAnimation(slideInRight);
        nameTextView2.startAnimation(slideInRight);
        logoImageView.startAnimation(slideInLeft);

        // Set a delay to transition to MainActivity after the splash duration
        new Handler().postDelayed(() -> {
            // Start MainActivity
            Intent intent = new Intent(SplashScreenActivity.this, GridScreenActivity.class);
            startActivity(intent);

            // Finish SplashScreenActivity so it's removed from the back stack
            finish();
        }, SPLASH_DURATION);
    }
}
