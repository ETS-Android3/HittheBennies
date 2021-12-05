package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView score_id;
    private ImageButton button1;

    int score = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        score_id = (TextView) findViewById(R.id.score_id);
        button1 = (ImageButton) findViewById(R.id.button1);

        score_id.setText("Score: " + score);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                score_id.setText("Score: " + score);
            }
        });

    }
}