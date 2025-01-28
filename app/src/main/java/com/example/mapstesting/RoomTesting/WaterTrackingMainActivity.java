package com.example.mapstesting.RoomTesting;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.mapstesting.R;

import java.time.LocalDate;

public class WaterTrackingMainActivity extends AppCompatActivity {

    AppDatabase db; // Database instance
    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_water_tracking_main);

        EditText etDescription = findViewById(R.id.etDescription);
        EditText etVolume = findViewById(R.id.etVolume);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "water_tracking_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();



        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        String[] items = getResources().getStringArray(R.array.dropdown_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            submit(etDescription, etVolume);
        });

        for (WaterTracking record: db.waterTrackingDAO().getAll()) {
            System.out.println("Water Intake: " + record.waterIntake + "ml");
            System.out.println(record.description);
            System.out.println(record.category);

        }

    }

    public void submit(EditText etDescription, EditText etVolume) {

        if (etDescription.getText().toString().isEmpty() || etVolume.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        WaterTracking waterTracking = new WaterTracking();
        waterTracking.date = LocalDate.now();
        waterTracking.waterIntake = Integer.parseInt(etVolume.getText().toString());
        waterTracking.category = selectedCategory;
        waterTracking.description = etDescription.getText().toString();

        db.waterTrackingDAO().insert(waterTracking);
        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

    }
}