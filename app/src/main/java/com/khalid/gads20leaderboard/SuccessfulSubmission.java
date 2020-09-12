package com.khalid.gads20leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class SuccessfulSubmission extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_submission);
        getWindow().setLayout(900,
                780);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getWindow().isActive()) {

        }
    }
}