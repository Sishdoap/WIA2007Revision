package com.example.mapstesting.KidsLearningApp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class EnglishViewModel extends ViewModel {
    private final List<EnglishQuestion> questions = new ArrayList<>();

    public EnglishViewModel() {
        if (questions.isEmpty()) { // ✅ Prevents reinitialization
            generateQuestions();
        }
    }

    private void generateQuestions() {
        for (int i = 0; i < 3; i++) {
            questions.add(new EnglishQuestion());
        }
    }

    public List<EnglishQuestion> getQuestions() {
        return questions; // ✅ Always return the same reference
    }
}
