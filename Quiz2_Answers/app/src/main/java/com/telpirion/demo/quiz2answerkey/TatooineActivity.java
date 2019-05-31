package com.telpirion.demo.quiz2answerkey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TatooineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tatooine);
    }

    public void onClick(View view) {
        TextView tv = findViewById(R.id.lbl_stormtrooper_brain);
        tv.setText("These are not the droids you're looking for.");
    }
}
