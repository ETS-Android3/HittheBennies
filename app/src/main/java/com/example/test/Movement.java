package com.example.test;

import android.graphics.Point;
import android.media.Image;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

    public class Movement extends AppCompatActivity {

        // Screen Size
        private int screenWidth;
        private int screenHeight;

        // Images
        private ImageView squirrelUp;
        private ImageView squirrelDown;
        private ImageView squirrelRight;
        private ImageView squirrelLeft;

        // Button
        private Button pauseBtn;

        // Position
        private float squirrelUpX;
        private float squirrelUpY;
        private float squirrelDownX;
        private float squirrelDownY;
        private float squirrelRightX;
        private float squirrelRightY;
        private float squirrelLeftX;
        private float squirrelLeftY;

        // Initialize Class
        private Handler handler = new Handler();
        private Timer timer = new Timer();

        // Status Check
        private boolean pause_flg = false;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            squirrelUp = (ImageView)findViewById(R.id.squirrelUp);
            squirrelDown = (ImageView)findViewById(R.id.squirrelDown);
            squirrelRight = (ImageView)findViewById(R.id.squirrelRight);
            squirrelLeft = (ImageView)findViewById(R.id.squirrelLeft);



            // Get Screen Size.
            WindowManager wm = getWindowManager();
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;

            // Move to out of screen.
            squirrelUp.setX(-80.0f);
            squirrelUp.setY(-80.0f);
            squirrelDown.setX(-80.0f);
            squirrelDown.setY(screenHeight + 80.0f);
            squirrelRight.setX(screenWidth + 80.0f);
            squirrelRight.setY(-80.0f);
            squirrelLeft.setX(-80.0f);
            squirrelLeft.setY(-80.0f);


            // Start the timer.
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        }

        public void changePos() {
            // Up
            squirrelUpY -= 10;
            if (squirrelUp.getY() + squirrelUp.getHeight() < 0) {
                squirrelUpX = (float)Math.floor(Math.random() * (screenWidth - squirrelUp.getWidth()));
                squirrelUpY = screenHeight + 100.0f;
            }
            squirrelUp.setX(squirrelUpX);
           squirrelUp.setY(squirrelUpY);

            // Down
            squirrelDownY += 10;
            if (squirrelDown.getY() > screenHeight) {
                squirrelDownX = (float)Math.floor(Math.random() * (screenWidth - squirrelDown.getWidth()));
                squirrelDownY = -100.0f;
            }
            squirrelDown.setX(squirrelDownX);
            squirrelDown.setY(squirrelDownY);

            // Right
            squirrelRightX += 10;
            if (squirrelRight.getX() > screenWidth) {
                squirrelRightX = -100.0f;
                squirrelRightY = (float)Math.floor(Math.random() * (screenHeight - squirrelRight.getHeight()));
            }
            squirrelRight.setX(squirrelRightX);
            squirrelRight.setY(squirrelRightY);

            // Left
            squirrelLeftX -= 10;
            if (squirrelLeft.getX() + squirrelLeft.getWidth() < 0) {
                squirrelLeftX = screenWidth + 100.0f;
                squirrelLeftY = (float)Math.floor(Math.random() * (screenHeight - squirrelLeft.getHeight()));
            }
            squirrelLeft.setX(squirrelLeftX);
            squirrelLeft.setY(squirrelLeftY);

        }


        public void pausePushed(View view) {

            if (pause_flg == false) {

                pause_flg = true;

                // Stop the timer.
                timer.cancel();
                timer = null;

                // Change Button Text.
                pauseBtn.setText("START");


            } else {

                pause_flg = false;

                // Change Button Text.
                pauseBtn.setText("PAUSE");

                // Create and Start the timer.
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                changePos();
                            }
                        });
                    }
                }, 0, 20);

            }

        }

}
