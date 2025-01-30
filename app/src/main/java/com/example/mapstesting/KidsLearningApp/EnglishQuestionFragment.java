package com.example.mapstesting.KidsLearningApp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mapstesting.R;

public class EnglishQuestionFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    private EnglishQuestion question;

    public static EnglishQuestionFragment newInstance(int position) {
        EnglishQuestionFragment fragment = new EnglishQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
        EnglishViewModel englishViewModel = new ViewModelProvider(requireActivity()).get(EnglishViewModel.class);
        question = englishViewModel.getQuestions().get(position);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_english_question, container, false);

        TextView tvColor = view.findViewById(R.id.tvColor);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton rbOption1 = view.findViewById(R.id.rbOption1);
        RadioButton rbOption2 = view.findViewById(R.id.rbOption2);
        Button btnNext = view.findViewById(R.id.btnNext);

        // Set question values
        tvColor.setText("What color is this? " + question.getColor());
        rbOption1.setText(question.getAnswerChoices()[0]);
        rbOption2.setText(question.getAnswerChoices()[1]);

        // Set background color dynamically
        view.setBackgroundColor(Color.parseColor(question.getColorHex()));



        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == rbOption1.getId()) {
                question.setUserAnswer(rbOption1.getText().toString());
                Log.d("QuizDebug", "Fragment Stored Answer: " + question.getUserAnswer());            } else if (checkedId == rbOption2.getId()) {
                question.setUserAnswer(rbOption2.getText().toString());
                Log.d("QuizDebug", "Fragment Stored Answer: " + question.getUserAnswer());            }

        });

        btnNext.setOnClickListener(v -> {
            ViewPager2 viewPager = getActivity().findViewById(R.id.viewPager);
            EnglishViewModel englishViewModel = new ViewModelProvider(requireActivity()).get(EnglishViewModel.class);

            if (viewPager.getCurrentItem() < englishViewModel.getQuestions().size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                // End of quiz
            }
        });

        return view;
    }
}
