package com.example.mapstesting.KidsLearningApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mapstesting.R;

public class MainActivityLearning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_learning);

        Button btnMathTest = findViewById(R.id.btnMathTest);
        btnMathTest.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MathActivity.class);
            startActivity(intent);
        });

        Button btnEngTest = findViewById(R.id.btnEngTest);
        btnEngTest.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), EnglishActivity.class);
            startActivity(intent);
        });

    }
}