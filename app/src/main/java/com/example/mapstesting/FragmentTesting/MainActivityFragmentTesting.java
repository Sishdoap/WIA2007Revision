package com.example.mapstesting.FragmentTesting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mapstesting.R;

public class MainActivityFragmentTesting extends AppCompatActivity implements SampleFragment.OnDataPass {

    private boolean isFragmentVisible = false;
    private Button btnToggleFragment;
    private FrameLayout fragmentContainer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_fragment_testing);

        btnToggleFragment = findViewById(R.id.btnToggleFragment);
        fragmentContainer = findViewById(R.id.fragment_container);
        textView = findViewById(R.id.textView);

        btnToggleFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFragment();
            }
        });


    }

    @Override
    public void onDataPass(String data) {
        textView.setText(data);
    }

    private void toggleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (isFragmentVisible) {
            Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
            if (fragment != null) {
                transaction.remove(fragment);
            }
            fragmentContainer.setVisibility(View.GONE);
            btnToggleFragment.setText("Show Fragment");
        } else {
            SampleFragment sampleFragment = new SampleFragment();
            transaction.replace(R.id.fragment_container, sampleFragment);
            transaction.addToBackStack(null);
            fragmentContainer.setVisibility(View.VISIBLE);
            btnToggleFragment.setText("Hide Fragment");
        }

        transaction.commit();
        isFragmentVisible = !isFragmentVisible;
    }
}