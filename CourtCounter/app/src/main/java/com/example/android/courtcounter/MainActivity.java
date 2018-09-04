package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreA);
    }

    public void plus3Points(View v) {
        scoreA = scoreA + 3;
        displayForTeamA(scoreA);
    }

    public void plus2Points(View v) {
        scoreA = scoreA + 2;
        displayForTeamA(scoreA);
    }

    public void plus1Point(View v) {
        scoreA = scoreA + 1;
        displayForTeamA(scoreA);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
}
