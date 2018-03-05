package com.example.android.swipeh1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;

import static android.content.ContentValues.TAG;


public class Main2Activity extends AppCompatActivity {

    public TextView timerView;
    int score = 0;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int drag = 0;
    String level1;
    String level2;
    String level3;
    private long startTime = 0L;
    private Handler customHandler = new Handler();

    // public int intValue = getIntent().getIntExtra("fsf", 0);
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
                    + String.format("%02d", secs) + ":" + String.format("%02d", milliseconds));
            customHandler.postDelayed(this, 0);


        }
    };

    public Main2Activity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final View myView = (View) findViewById(R.id.myView);
        final View fullScreen = (View) findViewById(R.id.fullscreen);
        timerView = (TextView) this.findViewById(R.id.time);


        Intent k = getIntent();
        int time = 0;

        if (k != null) {
            time = k.getIntExtra("time", 0);
            drag = k.getIntExtra("drags", 0);
            level1 = k.getStringExtra("easy");
            level2 = k.getStringExtra("medium");
            level3 = k.getStringExtra("hard");

            Log.d("kdjfskj", "time" + time);

            if (!(time == 0)) {
                countDown(time);
            } else if (!(drag == 0)) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            } else if (!(level1 == null)) {
                Log.d("main2", "easy level");
                myView.setLayoutParams(new LinearLayout.LayoutParams(2000, 2000));
                countDown(15000);
            } else if (!(level2 == null)) {
                Log.d("main2", "medium level");
                myView.setLayoutParams(new LinearLayout.LayoutParams(200, 2000));
                countDown(15000);
            } else if (!(level3 == null)) {
                Log.d("main2", "hard level");
                myView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                countDown(15000);
            } else {
                //kdfsdfjkl
            }

        }


        final MediaPlayer mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.music);


        fullScreen.setOnTouchListener(new OnSlidingListener(this) {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Rect rect;
                rect = new Rect(myView.getLeft(), myView.getTop(), myView.getRight(), myView.getBottom());
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "NOT TOUCH THE SCREEN");

                    if (!rect.contains((int) event.getX(), (int) event.getY())) {
                        // User moved outside bounds
                        Log.d(TAG, "Movement occurred outside bounds of current screen element");
                        mediaPlayer.pause();
                        score--;
                        displayScore(score);
                    }


                }


                return super.onTouch(v, event);
            }


        });


        myView.setOnTouchListener(new OnSlidingListener(this) {


                                      @Override
                                      public boolean onSwipeRight() {
                                          Log.d("right", "" + score + "");
                                          mediaPlayer.pause();

                                          return true;
                                      }

                                      @Override
                                      public boolean onSwipeLeft() {
                                          Log.v("swipe", "left");
                                          mediaPlayer.pause();
                                          return true;
                                      }

                                      @Override
                                      public boolean onSwipeTop() {
                                          Log.v("swipe", "top");
                                          mediaPlayer.start();
                                          score++;
                                          displayScore(score);

                                          Log.d("top", "" + score + "");
                                          pauseTimer();
                                          return true;
                                      }

                                      @Override
                                      public boolean onSwipeBottom() {
                                          Log.v("swipe", "down");
                                          mediaPlayer.start();
                                          score++;
                                          displayScore(score);
                                          pauseTimer();
                                          return true;
                                      }


                                  }


        );


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

    public void pauseTimer() {

        if (drag == score) {
            Log.d("hdfkjshkja", "score" + score);

            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);


            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Main2Activity.this);


                // set title
                alertDialogBuilder.setTitle("GAME OVER");

                alertDialogBuilder
                        .setMessage("time  " + "" + (updatedTime / 60000) + ":" + (updatedTime / 1000) +
                                ":" + updatedTime % 1000 + "\nPlay again");
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


        }
    }


    // / no need to call prepare(); create() does that for you
    /*public void checkbounder(View v, int x, int y ) {
        Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        if (event == MotionEvent.ACTION_UP) {
            if (!rect.contains((int) event.getX(), (int) event.getY())) {
                // User moved outside bounds
                Log.d(TAG, "Movement occurred outside bounds of current screen element");
                ;
            }
            else score++;


        }

    }*/
}

