package com.example.mapstesting.KidsLearningApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

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

        SharedPreferences prefs = getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE);
        int englishScore = prefs.getInt("englishMark", 0);
        int mathScore = prefs.getInt("mathMark", 0);

        System.out.println(englishScore + " is the English score");
        System.out.println(mathScore + " is the English score");

        RatingBar ratingBarMath = findViewById(R.id.ratingBarMath);
        ratingBarMath.setRating(mathScore);

        RatingBar ratingBarEnglish = findViewById(R.id.ratingBarEnglish);
        ratingBarEnglish.setRating(englishScore);




    }
}