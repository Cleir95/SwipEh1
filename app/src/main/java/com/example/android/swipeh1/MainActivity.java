package com.example.android.swipeh1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void startButton(View view) {

        EditText enteredTimeLimit = (EditText) findViewById(R.id.timeLimit);
        EditText enteredDragLimit = (EditText) findViewById(R.id.dragLimit);
        RadioButton easy = (RadioButton) findViewById(R.id.easy);
        String level1="easy";
        RadioButton medium = (RadioButton) findViewById(R.id.medium);
        String level2="medium";
        RadioButton hard= (RadioButton) findViewById(R.id.hard);
        String level3="hard";


        String value = enteredTimeLimit.getText().toString();
        Log.d("jdskfjsl", "time" + value);
        String value1 = enteredDragLimit.getText().toString();
        Log.d("jdskfjsl", "DRAG" + value1);

        if (!(value.equals(""))) {
            Log.d("jdskfjsl", "time" + value);
            int time = Integer.parseInt(value);
            Log.d("jdskfjsl", "time" + time);
            int timeInMillis = time * 1000;
            Log.d("jdskfjsl", "time" + timeInMillis);

            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("time", timeInMillis);
            startActivity(intent);
        }

       else if (!(value1.equals(""))) {
            Log.d("jdskfjsl", "DRAG reavhed inside" );
            int drags = Integer.parseInt(value1);
            Log.d("jdskfjsl", "DRAG" + drags);

            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("drags", drags);
            startActivity(intent);
        }
        else if (easy.isChecked()) {
            Log.d("eady", "easy level");
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("easy", level1);
            startActivity(intent);
        }

        else if ( medium.isChecked()) {
            Log.d("main1", "medium level");
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("medium", level2);
            startActivity(intent);
        }

        else if ( hard.isChecked()) {
            Log.d("medium", "hardddddd");
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("hard", level3);
            startActivity(intent);
        }


    }

    public void timeChallenge(View view) {
        EditText enteredTimeLimit = (EditText) findViewById(R.id.timeLimit);
        enteredTimeLimit.setVisibility(View.VISIBLE);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);


        Button timeChallengeButton = (Button) findViewById(R.id.timeChallengeButton);
        timeChallengeButton.setVisibility(View.INVISIBLE);

        Button dragChallengeButton = (Button) findViewById(R.id.dragChallengeButton);
        dragChallengeButton.setVisibility(View.INVISIBLE);

        Button Levels = (Button) findViewById(R.id.levels);
        Levels.setVisibility(View.INVISIBLE);


    }

    public void DragChallenge(View view) {
        EditText enteredTimeLimit = (EditText) findViewById(R.id.dragLimit);
        enteredTimeLimit.setVisibility(View.VISIBLE);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);

        Button dragChallengeButton = (Button) findViewById(R.id.dragChallengeButton);
        dragChallengeButton.setVisibility(View.INVISIBLE);

        Button timeChallengeButton = (Button) findViewById(R.id.timeChallengeButton);
        timeChallengeButton.setVisibility(View.INVISIBLE);

        Button Levels = (Button) findViewById(R.id.levels);
        Levels.setVisibility(View.INVISIBLE);

    }

    public void Levels(View view) {
        RadioButton easy = (RadioButton) findViewById(R.id.easy);
        easy.setVisibility(View.VISIBLE);

        RadioButton medium = (RadioButton) findViewById(R.id.medium);
        medium.setVisibility(View.VISIBLE);


        RadioButton hard = (RadioButton) findViewById(R.id.hard);
        hard.setVisibility(View.VISIBLE);


        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);

        Button Levels = (Button) findViewById(R.id.levels);
        Levels.setVisibility(View.INVISIBLE);

        Button dragChallengeButton = (Button) findViewById(R.id.dragChallengeButton);
        dragChallengeButton.setVisibility(View.INVISIBLE);

        Button timeChallengeButton = (Button) findViewById(R.id.timeChallengeButton);
        timeChallengeButton.setVisibility(View.INVISIBLE);

    }






}



