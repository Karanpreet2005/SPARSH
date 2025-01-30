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

    private static final int SPLASH_DURATION = 3000; // Duration for the splash screen in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ensure the app follows the system's light/dark mode settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Set the content view to the splash screen layout
        setContentView(R.layout.activity_splash_screen);

        // Apply gradient background to the splash screen activity layout
        getWindow().setBackgroundDrawableResource(R.drawable.gradient_background);

        // Find the views for animations
        TextView nameTextView = findViewById(R.id.name);
        TextView nameTextView2 = findViewById(R.id.name2);
        ImageView logoImageView = findViewById(R.id.img_flavordb);

        // Load entry animations
        Animation wobbleInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Animation slideInTop = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation slideInBottom = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);

        // Load the smooth fade-out animation for exit
        Animation fadeOutSmooth = AnimationUtils.loadAnimation(this, R.anim.fade_out_smooth);

        // Start entry animations
        logoImageView.startAnimation(wobbleInLeft);         // Wobble in from left for logo
        nameTextView.startAnimation(slideInTop);            // Slide in from top for the first text view
        nameTextView2.startAnimation(slideInBottom);        // Slide in from bottom for the second text view

        // Set animation listener for fade-out to make views invisible after fading out
        fadeOutSmooth.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Make views invisible after fade-out to prevent flashing
                nameTextView.setVisibility(TextView.INVISIBLE);
                nameTextView2.setVisibility(TextView.INVISIBLE);
                logoImageView.setVisibility(ImageView.INVISIBLE);

                // Transition to the next activity
                Intent intent = new Intent(SplashScreenActivity.this, GridScreenActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); // Smooth transition
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Do nothing
            }
        });

        // After the duration, start the fade-out animation
        new Handler().postDelayed(() -> {
            nameTextView.startAnimation(fadeOutSmooth);
            nameTextView2.startAnimation(fadeOutSmooth);
            logoImageView.startAnimation(fadeOutSmooth);
        }, SPLASH_DURATION);
    }
}
