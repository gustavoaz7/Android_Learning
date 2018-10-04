package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new NumbersClickListener());
    }

    public void openNumbersList(View v) {
        Intent i = new Intent(this, NumbersActivity.class);
        startActivity(i);
    }

    public void openPhrasesList(View v) {
        Intent i = new Intent(this, PhrasesActivity.class);
        startActivity(i);
    }

    public void openColorsList(View v) {
        Intent i = new Intent(this, ColorsActivity.class);
        startActivity(i);
    }

    public void openFamilyList(View v) {
        Intent i = new Intent(this, FamilyActivity.class);
        startActivity(i);
    }
}
