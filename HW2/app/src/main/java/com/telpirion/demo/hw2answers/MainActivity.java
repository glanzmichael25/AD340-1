package com.telpirion.demo.hw2answers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_ID =
            "com.telpirion.demo.hw2answers.MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onClick(View view) {
        EditText editText = (EditText)findViewById(R.id.txtbx_main_activity);
        String message = editText.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(MESSAGE_ID, message);

        startActivity(intent);
    }
}
