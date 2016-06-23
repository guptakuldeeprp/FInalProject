package com.example.kuldeepgupta.jokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    private static final String TAG = DisplayJoke.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/


        String jokeStr = null;
        if (savedInstanceState == null) {
            jokeStr = getIntent().getExtras().getString(getString(R.string.joke_str));
        } else {
            jokeStr = savedInstanceState.getString(getString(R.string.joke_str));
        }
        Log.i(TAG, "Received joke string: " + jokeStr);
        if(jokeStr != null) {
            TextView titleView = (TextView) findViewById(R.id.joke_text_view);
            Log.i(TAG, "Setting joke to the text view ");
            titleView.setText(jokeStr);

        }

    }
}
