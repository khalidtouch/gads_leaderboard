package com.khalid.gads20leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khalid.gads20leaderboard.web.ApiInterface;
import com.khalid.gads20leaderboard.web.SubmissionRequest;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity {
    public EditText firstNameEt, lastNameEt, emailEt, linkEt;
    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    public SubmissionActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        Button submitButton = this.findViewById(R.id.submit_btn_two);
        firstNameEt = this.findViewById(R.id.first_name_et);
        lastNameEt = this.findViewById(R.id.last_name_et);
        emailEt = this.findViewById(R.id.email_address_et);
        linkEt = this.findViewById(R.id.github_link_et);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmissionActivity.this, SubmissionDialog.class);
                SubmissionActivity.this.startActivity(intent);
            }
        });
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.alert_dialog_frame, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
    }

    public void submit() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            makeSubmission();
        } catch (Exception e) {
            Toast.makeText(this, "Could not submit", Toast.LENGTH_LONG);
        }
    }

    private void makeSubmission() {
        SubmissionRequest request = new SubmissionRequest();
        request.setName(firstNameEt.getText().toString().trim());
        request.setLastName(lastNameEt.getText().toString().trim());
        request.setEmail(emailEt.getText().toString().trim());
        request.setLink(linkEt.getText().toString().trim());


    }


}