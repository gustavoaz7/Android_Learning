package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreA = 0;
    int scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }

    public void plus3Points(View v) {
        if (v.getTag().toString().equals("team_a")) {
            scoreA = scoreA + 3;
            displayForTeamA(scoreA);
        } else if (v.getTag().toString().equals("team_b")) {
            scoreB = scoreB + 3;
            displayForTeamB(scoreB);
        }
    }

    public void plus2Points(View v) {
        if (v.getTag().toString().equals("team_a")) {
            scoreA = scoreA + 2;
            displayForTeamA(scoreA);
        } else if (v.getTag().toString().equals("team_b")) {
            scoreB = scoreB + 2;
            displayForTeamB(scoreB);
        }
    }

    public void plus1Point(View v) {
        if (v.getTag().toString().equals("team_a")) {
            scoreA = scoreA + 1;
            displayForTeamA(scoreA);
        } else if (v.getTag().toString().equals("team_b")) {
            scoreB = scoreB + 1;
            displayForTeamB(scoreB);
        }
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void resetCounter(View v) {
        scoreA = 0;
        scoreB = 0;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }

}
