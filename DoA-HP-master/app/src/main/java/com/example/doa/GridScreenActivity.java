package com.example.doa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class GridScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_screen);
    }

    // Open MainActivity when the first card is clicked
    public void openMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Open a placeholder activity for other cards
    public void openPlaceholderActivity(View view) {
        Intent intent = new Intent(this, PlaceholderActivity.class);
        startActivity(intent);
    }
}
