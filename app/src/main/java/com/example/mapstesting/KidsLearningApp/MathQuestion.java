package com.example.mapstesting.KidsLearningApp;

import java.util.Random;

public class MathQuestion {

    private int operand1;
    private char operator;
    private int operand2;
    private int answer;
    private int[] answerChoices;
    private int userAnswer = -1; // Stores the answer selected by the user

    public MathQuestion() {
        Random random = new Random();

        this.operand1 = random.nextInt(10) + 1;
        this.operand2 = random.nextInt(10) + 1;

        this.operator = random.nextBoolean() ? '+' : '-';

        if (operator == '+') {
            this.answer = operand1 + operand2;
        } else {
            if (operand2 > operand1) {
                int temp = operand1;
                operand1 = operand2;
                operand2 = temp;
            }
            this.answer = operand1 - operand2;
        }

        this.answerChoices = generateAnswerChoices(answer);
    }

    private int[] generateAnswerChoices(int correctAnswer) {
        Random random = new Random();
        int wrongAnswer;
        do {
            wrongAnswer = correctAnswer + (random.nextBoolean() ? 1 : -1) * (random.nextInt(3) + 1);
        } while (wrongAnswer == correctAnswer || wrongAnswer < 0);

        if (random.nextBoolean()) {
            return new int[]{correctAnswer, wrongAnswer};
        } else {
            return new int[]{wrongAnswer, correctAnswer};
        }
    }

    // Getter methods
    public int getOperand1() { return operand1; }
    public char getOperator() { return operator; }
    public int getOperand2() { return operand2; }
    public int getAnswer() { return answer; }
    public int[] getAnswerChoices() { return answerChoices; }
    public int getUserAnswer() { return userAnswer; }
    public void setUserAnswer(int userAnswer) { this.userAnswer = userAnswer; }


    @Override
    public String toString() {
        return operand1 + " " + operator + " " + operand2 + " = ?";
    }

}
