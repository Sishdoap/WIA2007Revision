package com.example.mapstesting.RoomTesting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.mapstesting.R;

import java.time.LocalDate;


public class WaterTrackingHomeActivity extends AppCompatActivity {

    AppDatabase db; // Database instance
    public final int recommendedWaterIntake = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_water_tracking_home);

        TextView tvWaterTaken = findViewById(R.id.tvWaterTaken);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "water_tracking_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        tvWaterTaken.setText(String.valueOf(db.waterTrackingDAO().getTotalWaterIntakeByDate(LocalDate.now())));

        ProgressBar waterProgressBar = findViewById(R.id.waterProgressBar);
        waterProgressBar.setMax(recommendedWaterIntake);
        waterProgressBar.setProgress(db.waterTrackingDAO().getTotalWaterIntakeByDate(LocalDate.now()));

        Button btnRecord = findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), WaterTrackingMainActivity.class);
            startActivity(intent);
        });

        Button btnSummary = findViewById(R.id.btnSummary);
        btnSummary.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), WaterTrackingSummaryActivity.class);
            startActivity(intent);
        });





    }
}
