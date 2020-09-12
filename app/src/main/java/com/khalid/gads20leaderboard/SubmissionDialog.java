package com.khalid.gads20leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SubmissionDialog extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_dialog);
        getWindow().setLayout(900,
                1080);
        ImageView negativeButton = this.findViewById(R.id.negative_button);
        Button positiveButton = this.findViewById(R.id.positive_button);
        negativeButton.setOnClickListener(this);
        positiveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.negative_button:
                intent = new Intent(SubmissionDialog.this, SubmissionAborted.class);
                SubmissionDialog.this.startActivity(intent);
                SubmissionDialog.this.finish();
                break;
            case R.id.positive_button:
                //submit
                intent = new Intent(SubmissionDialog.this, SuccessfulSubmission.class);
                SubmissionDialog.this.startActivity(intent);
                SubmissionDialog.this.finish();
                break;
        }
    }
}