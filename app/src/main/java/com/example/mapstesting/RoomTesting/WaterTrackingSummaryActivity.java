package com.example.mapstesting.RoomTesting;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.mapstesting.RoomTesting.WaterTrackingHomeActivity;

import com.example.mapstesting.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WaterTrackingSummaryActivity extends AppCompatActivity {

    AppDatabase db; // Database instance
    ProgressBar dayOneBar, dayTwoBar, dayThreeBar;
    TextView tvDayOne, tvDayTwo, tvDayThree, dayOneLabel, dayTwoLabel, dayThreeLabel, tvSugaryDrink, tvNonSugaryDrink, tvWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_water_tracking_summary);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "water_tracking_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        dayOneBar = findViewById(R.id.dayOneBar);
        dayTwoBar = findViewById(R.id.dayTwoBar);
        dayThreeBar = findViewById(R.id.dayThreeBar);

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate dayBeforeYesterday = today.minusDays(2);

        dayOneBar.setMax(3000);
        dayTwoBar.setMax(3000);
        dayThreeBar.setMax(3000);

        dayOneBar.setProgress(db.waterTrackingDAO().getTotalWaterIntakeByDate(today));
        dayTwoBar.setProgress(db.waterTrackingDAO().getTotalWaterIntakeByDate(yesterday));
        dayThreeBar.setProgress(db.waterTrackingDAO().getTotalWaterIntakeByDate(dayBeforeYesterday));

        tvDayOne = findViewById(R.id.tvDayOne);
        tvDayTwo = findViewById(R.id.tvDayTwo);
        tvDayThree = findViewById(R.id.tvDayThree);

        tvDayOne.setText(db.waterTrackingDAO().getTotalWaterIntakeByDate(today) + " ml");
        tvDayTwo.setText(db.waterTrackingDAO().getTotalWaterIntakeByDate(yesterday) + " ml");
        tvDayThree.setText(db.waterTrackingDAO().getTotalWaterIntakeByDate(dayBeforeYesterday) + " ml");

        dayOneLabel = findViewById(R.id.dayOneLabel);
        dayTwoLabel = findViewById(R.id.dayTwoLabel);
        dayThreeLabel = findViewById(R.id.dayThreeLabel);

        dayOneLabel.setText(today.toString());
        dayTwoLabel.setText(yesterday.toString());
        dayThreeLabel.setText(dayBeforeYesterday.toString());

        tvSugaryDrink = findViewById(R.id.tvSugaryDrink);
        tvNonSugaryDrink = findViewById(R.id.tvNonSugaryDrink);
        tvWater = findViewById(R.id.tvWater);

        tvSugaryDrink.setText(db.waterTrackingDAO().getTotalWaterIntakeByCategoryAndDateRange("Sweetened", dayBeforeYesterday, today) + " ml");
        tvNonSugaryDrink.setText(db.waterTrackingDAO().getTotalWaterIntakeByCategoryAndDateRange("Non-Sweetened", dayBeforeYesterday, today) + " ml");
        tvWater.setText(db.waterTrackingDAO().getTotalWaterIntakeByCategoryAndDateRange("Plain Water", dayBeforeYesterday, today) + " ml");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<WaterTracking> waterTrackingList = new ArrayList<>();
        waterTrackingList.addAll(db.waterTrackingDAO().getAll());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new WaterTrackingAdapter(waterTrackingList));




    }
}