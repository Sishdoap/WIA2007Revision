package com.example.mapstesting.KidsLearningApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapstesting.R;

import java.util.List;

public class MathActivity extends AppCompatActivity {

    private MathViewModel mathViewModel;
    private MathQuizAdapter mathQuizAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_math);

        SharedPreferences prefs = getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mathViewModel = new ViewModelProvider(this).get(MathViewModel.class);
        List<MathQuestion> questions = mathViewModel.getQuestions();

        mathQuizAdapter = new MathQuizAdapter(questions);
        recyclerView.setAdapter(mathQuizAdapter);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            int score = 0;

            // Get ViewModel instance
            MathViewModel mathViewModel = new ViewModelProvider(this).get(MathViewModel.class);

            for (MathQuestion question : mathViewModel.getQuestions()) {
                if (question.getUserAnswer() == question.getAnswer()) {
                    score++;
                }
            }

            editor.putInt("mathMark", score);
            editor.apply();

            // Show score in a Toast message
            Toast.makeText(MathActivity.this, "Score: " + score + "/" + mathViewModel.getQuestions().size(), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivityLearning.class);
            startActivity(intent);
        });




    }
}