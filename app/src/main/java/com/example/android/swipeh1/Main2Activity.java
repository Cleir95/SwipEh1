package com.example.android.swipeh1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    int score = 0;
    public TextView timerView;
    long timeInMilliseconds = 0L;
    private long startTime = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private Handler customHandler = new Handler();


    // public int intValue = getIntent().getIntExtra("fsf", 0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        View myView = (View) findViewById(R.id.myView);
        timerView = (TextView) this.findViewById(R.id.time);


        Intent k = getIntent();
        int time = 0;
        int drag = 0;
        if (k != null) {
            time = k.getIntExtra("time", 0);
            drag = k.getIntExtra("drags", 0);
            Log.d("kdjfskj", "hjks" + time);

            if (!(time == 0)) {
                countDown(time);
            } else if (!(drag == 0)) {

                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
               updateTimerThread. run();
            }

        }


        myView.setOnTouchListener(new OnSlidingListener(this) {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // if (event.getAction() == MotionEvent.ACTION_UP) {
                // timer.start();
                //  }
                return super.onTouch(v, event);
            }


            @Override
            public boolean onSwipeRight() {
                Log.d("right", "" + score + "");
                return true;
            }

            @Override
            public boolean onSwipeLeft() {
                Log.v("swipe", "left");
                return true;
            }

            @Override
            public boolean onSwipeTop() {
                Log.v("swipe", "top");
                score++;
                displayScore(score);
                Log.d("top", "" + score + "");
                return true;
            }

            @Override
            public boolean onSwipeBottom() {
                Log.v("swipe", "down");
                score++;
                displayScore(score);
                return true;
            }
        });
    }


    public void displayScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText(String.valueOf(score));
    }


    public void displayTimeLeft(int timeLeft) {
        TextView scoreView = (TextView) findViewById(R.id.timeDisplay);
        scoreView.setText(String.valueOf(timeLeft));
    }


    public void countDown(int time) {
        final CountDownTimer timer;
        {
            timer = new CountDownTimer(time, 1000) {

                @Override
                public void onTick(long timeleftInMillis) {
                    int timeLeft = (int) (timeleftInMillis / 1000);
                    displayTimeLeft(timeLeft);

                }

                @Override
                public void onFinish() {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            Main2Activity.this);


                    // set title
                    alertDialogBuilder.setTitle("GAME OVER");

                    alertDialogBuilder
                            .setMessage("Score  " + score + "\nPlay again");
                    alertDialogBuilder.setCancelable(false);


                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                            // if this button is clicked, close
                            // current activity
                            Main2Activity.this.finish();
                        }
                    });


                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }
            };
        }
        timer.start();
    }


    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerView.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);


        }
    };
}

