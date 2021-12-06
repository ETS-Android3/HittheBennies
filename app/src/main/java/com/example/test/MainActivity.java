package com.example.test;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GameDifficultyDialogFragment.OnGameDifficultySelectedListener{

    private CountDownTimer mTimer;
    private TextView score_id;
    private ImageButton button1;
    private Menu mMenu;
    private long mTimerLength = 30000;
    int score = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        // Determine which menu option was chosen


        if (item.getItemId() == R.id.action_one)
        {
            GameDifficultyDialogFragment dialog = new GameDifficultyDialogFragment();
            dialog.show(getSupportFragmentManager(), "gameDifficultyDialog");
            return true;
        }
        else if (item.getItemId() == R.id.action_stop)
        {
            mTimer.cancel();
            item.setVisible(false);
            mTimer.onFinish();
            return true;
        }
        else if (item.getItemId() == R.id.action_start)
        {
            gameStart();
            mMenu.findItem(R.id.action_stop).setVisible(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void gameStart() {
        mMenu.findItem(R.id.action_stop).setVisible(false);
        score_id = (TextView) findViewById(R.id.score_id);
        button1 = (ImageButton) findViewById(R.id.button1);
        score = 0;
        score_id.setText("Score: " + score);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                score_id.setText("Score: " + score);
            }
        });

        if (mTimer != null)
        {
            mTimer.cancel();
        }
        mTimer = new CountDownTimer(30000, 2000)
        {
            public void onTick(long millisUntilFinished)
            {

            }


            public void onFinish()
            {
                mMenu.findItem(R.id.action_stop).setVisible(false);
                button1.setClickable(false);
            }
        }.start();
    }

    @Override
    public void onGameDifficultyClick ( int which)
    {
        mTimerLength = 30000 / (which + 1);
    }
}
