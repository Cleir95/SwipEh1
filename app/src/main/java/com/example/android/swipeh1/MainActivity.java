package com.example.android.swipeh1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void StartButton(View view) {
        EditText enteredTimeLimit = (EditText) findViewById(R.id.timeLimit);

        String value = enteredTimeLimit.getText().toString();
        int time = Integer.parseInt (value);
        int timeInMillis = time *1000;

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("time",timeInMillis);
        startActivity(intent);
    }
}
