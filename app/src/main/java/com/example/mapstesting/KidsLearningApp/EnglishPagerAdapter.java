package com.example.mapstesting.KidsLearningApp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EnglishPagerAdapter extends FragmentStateAdapter {

    private int questionCount;

    public EnglishPagerAdapter(@NonNull FragmentActivity fragmentActivity, int questionCount) {
        super(fragmentActivity);
        this.questionCount = questionCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return EnglishQuestionFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return questionCount;
    }
}
