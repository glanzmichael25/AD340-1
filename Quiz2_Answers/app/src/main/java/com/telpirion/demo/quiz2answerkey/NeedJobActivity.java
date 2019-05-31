package com.telpirion.demo.quiz2answerkey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NeedJobActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_job);
    }

    public void onClick(View view) {
        TextView textView = findViewById(R.id.ur_new_job);
        textView.setText("Congratulations! You are a robot builder.");
    }
}
