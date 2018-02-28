package com.example.android.swipeh1;

/**
 * Created by xclei on 27/02/2018.
 */

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSlidingListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    int secondsleft;

    public OnSlidingListener(Context context){
        gestureDetector = new GestureDetector(context, new GestureListener());
    }



    public boolean onTouch(View v, MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {



        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return onClick();
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float angle = (float) Math.toDegrees(Math.atan2(e2.getY() - e1.getY(), e2.getX() - e1.getX()));
                if (angle > 0) {
                    if (angle > 85 && angle <= 105) {
                        onSwipeTop();
                        result = true;
                        Log.d("angle", "  " + angle);
                    } else if (angle > 105 && angle <= 255) {
                        onSwipeLeft();
                        result = true;
                        Log.d("angle", "  " + angle);
                    } else if (angle > 255 && angle <= 285) {
                        onSwipeBottom();
                        result = true;
                        Log.d("angle", "  " + angle);
                    } else {
                        onSwipeRight();
                        result = true;
                        Log.d("angle", "  " + angle);
                    }

                }


                if (angle < 0) {
                    angle = angle+360;
                    if (angle > 85 && angle <= 105) {
                        onSwipeTop();
                        result = true;
                        Log.d("angle", "  " + angle);
                    } else if (angle > 105 && angle <= 255) {
                        onSwipeLeft();
                        result = true;
                        Log.d("angle", "  " + angle);
                    } else if (angle > 255 && angle <= 285) {
                        onSwipeBottom();
                        result = true;
                        Log.d("angle", "  " + angle);
                    } else {
                        onSwipeRight();
                        result = true;
                        Log.d("angle", "  " + angle);
                    }
                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public boolean onClick() {
        return false;
    }

    public boolean onLongClick() {
        return false;
    }


    public boolean onSwipeRight( ) { return false;}

    public boolean onSwipeLeft() {   return false;}

    public boolean onSwipeTop() {  return false;}

    public boolean onSwipeBottom() {  return false;}
}
