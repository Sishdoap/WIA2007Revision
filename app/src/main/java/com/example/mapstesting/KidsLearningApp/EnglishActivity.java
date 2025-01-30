package com.example.mapstesting.KidsLearningApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mapstesting.R;

public class EnglishActivity extends AppCompatActivity {

    private EnglishViewModel englishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        SharedPreferences prefs = getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        englishViewModel = new ViewModelProvider(this).get(EnglishViewModel.class);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        EnglishPagerAdapter adapter = new EnglishPagerAdapter(this, englishViewModel.getQuestions().size());
        viewPager.setAdapter(adapter);


        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            int score = 0;

            for (EnglishQuestion question : englishViewModel.getQuestions()) {
                String userAnswer = question.getUserAnswer(); // ✅ Retrieve stored answer
                String correctAnswer = question.getAnswer();

                Log.d("QuizDebug", "Activity Checking User Answer: " + userAnswer + " | Correct Answer: " + correctAnswer);

                if (userAnswer != null && userAnswer.trim().equalsIgnoreCase(correctAnswer.trim())) {
                    score++;
                }
            }

            editor.putInt("englishMark", score);
            editor.apply();

            Toast.makeText(EnglishActivity.this, "Score: " + score + "/" + englishViewModel.getQuestions().size(), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivityLearning.class);
            startActivity(intent);
        });
    }
}
