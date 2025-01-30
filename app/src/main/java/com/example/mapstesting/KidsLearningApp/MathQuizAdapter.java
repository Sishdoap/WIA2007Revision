package com.example.mapstesting.KidsLearningApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapstesting.R;

import java.util.List;

public class MathQuizAdapter extends RecyclerView.Adapter<MathQuizAdapter.QuizViewHolder> {
    private List<MathQuestion> questions;

    public MathQuizAdapter(List<MathQuestion> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.math_layout, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        MathQuestion question = questions.get(position);
        int[] choices = question.getAnswerChoices();

        holder.tvOperand1.setText(String.valueOf(question.getOperand1()));
        holder.tvOperator.setText(String.valueOf(question.getOperator()));
        holder.tvOperand2.setText(String.valueOf(question.getOperand2()));

        holder.rbOption1.setText(String.valueOf(choices[0]));
        holder.rbOption2.setText(String.valueOf(choices[1]));

        // Reset checked state (prevents recycled views from holding state)
        holder.radioGroup.clearCheck();

        // Handle answer selection
        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == holder.rbOption1.getId()) {
                question.setUserAnswer(Integer.parseInt(holder.rbOption1.getText().toString()));
            } else if (checkedId == holder.rbOption2.getId()) {
                question.setUserAnswer(Integer.parseInt(holder.rbOption2.getText().toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView tvOperand1, tvOperator, tvOperand2;
        RadioGroup radioGroup;
        RadioButton rbOption1, rbOption2;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOperand1 = itemView.findViewById(R.id.tvOperand1);
            tvOperator = itemView.findViewById(R.id.tvOperator);
            tvOperand2 = itemView.findViewById(R.id.tvOperand2);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            rbOption1 = itemView.findViewById(R.id.rbOption1);
            rbOption2 = itemView.findViewById(R.id.rbOption2);
        }
    }
}
