package com.example.mapstesting.KidsLearningApp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MathViewModel extends ViewModel {

    private List<MathQuestion> questions;

    public MathViewModel() {
        questions = new ArrayList<>();
        generateQuestions();
    }

    private void generateQuestions() {
        for (int i = 0; i < 3; i++) { // Generates 3 questions
            questions.add(new MathQuestion());
        }
    }

    public List<MathQuestion> getQuestions() {
        return questions;
    }
}
