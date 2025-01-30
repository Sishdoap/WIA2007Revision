package com.example.mapstesting.KidsLearningApp;


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EnglishQuestion {
    private String color;
    private String answer;
    private String[] answerChoices;

    private String userAnswer = null;  // Stores the selected answer
    // Map color names to their hex values
    private static final Map<String, String> colorMap = new HashMap<>();
    static {
        colorMap.put("Yellow", "#FFFF00");
        colorMap.put("Blue", "#0000FF");
        colorMap.put("Purple", "#800080");
        colorMap.put("Red", "#FF0000");
        colorMap.put("Green", "#008000");
        colorMap.put("Orange", "#FFA500");
    }

    private List<String> colors = new ArrayList<>(colorMap.keySet());

    public EnglishQuestion() {
        Random random = new Random();

        // Select a correct answer randomly
        int ansIndex = random.nextInt(colors.size());
        this.color = colors.get(ansIndex);
        this.answer = colors.get(ansIndex);

        // Generate two answer choices
        this.answerChoices = generateAnswerChoices(colors, this.answer);
    }

    private String[] generateAnswerChoices(List<String> colors, String correctAnswer) {
        Random random = new Random();
        List<String> choices = new ArrayList<>(colors);

        // Remove the correct answer from the choices
        choices.remove(correctAnswer);

        // Pick a wrong answer randomly
        String wrongAnswer = choices.get(random.nextInt(choices.size()));

        // Randomly shuffle the correct and wrong answers
        if (random.nextBoolean()) {
            return new String[]{correctAnswer, wrongAnswer};
        } else {
            return new String[]{wrongAnswer, correctAnswer};
        }
    }

    public String getColor() { return color; }
    public String getAnswer() { return answer; }
    public String[] getAnswerChoices() { return answerChoices; }

    // Get hex code for the color
    public String getColorHex() {
        return colorMap.get(color);
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        Log.d("QuizDebug", "Stored Answer: " + this.userAnswer);
    }

    public String getUserAnswer() {
        return userAnswer;
    }
}



