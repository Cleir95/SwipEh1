package com.example.android.swipeh1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
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
    public TextView textView;

   // public int intValue = getIntent().getIntExtra("fsf", 0);






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        View myView = (View) findViewById(R.id.myView);
        textView = (TextView) this.findViewById(R.id.time);

        Intent k = getIntent();
        int time =k.getIntExtra("time", 0);

        final CountDownTimer timer;

        {



            timer = new CountDownTimer(time, 1000) {


                @Override
                public void onTick(long timeleftInMillis) {
                    int timeLeft = (int)(timeleftInMillis/1000);
                    displayTimeLeft(timeLeft);

                }

                @Override
                public void onFinish() {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            Main2Activity.this);



                    // set title
                    alertDialogBuilder.setTitle("GAME OVER");

                    alertDialogBuilder
                            .setMessage("Score  " + score + "\n Play again");
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
}
