package com.khalid.gads20leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.khalid.gads20leaderboard.web.ApiInterface;
import com.khalid.gads20leaderboard.web.ApiPostService;
import com.khalid.gads20leaderboard.web.ApiService;
import com.khalid.gads20leaderboard.web.SubmissionRequest;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText firstNameEt, lastNameEt, emailEt, linkEt;
    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    public static final String TAG = "SubmissionActivity";
    private Button positiveBtn;
    private ImageView negativeBtn;
    private AlertDialog mDialog;

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
                showDialog();
            }
        });
    }


    private void showDialog() {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog_frame, null);
        positiveBtn = view.findViewById(R.id.positive_button);
        negativeBtn = view.findViewById(R.id.negative_button);
        builder.setView(view);
        positiveBtn.setOnClickListener(this);
        negativeBtn.setOnClickListener(this);

        mDialog = builder.create();
        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mDialog.show();
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_submission_successful, null);
        builder.setView(view);

        mDialog = builder.create();
        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mDialog.show();
    }

    private void showAbortedDialog() {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_submission_aborted, null);
        builder.setView(view);

        mDialog = builder.create();
        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mDialog.show();
    }


    private void makeSubmission() {
        String firstName = firstNameEt.getText().toString().trim();
        String lastName = lastNameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String link = linkEt.getText().toString().trim();

        if(!TextUtils.isEmpty(firstName) &&  !TextUtils.isEmpty(lastName)
                    && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(link)) {
            ApiInterface apiInterface = ApiPostService.getClient().create(ApiInterface.class);
            retrofit2.Call<SubmissionRequest> call = apiInterface.submit(
                    email, firstName, lastName, link);
            call.enqueue(new Callback<SubmissionRequest>() {
                @Override
                public void onResponse(Call<SubmissionRequest> call, Response<SubmissionRequest> response) {
                    Log.d(TAG, "onResponse: Submitted");
                    //Toast.makeText(SubmissionActivity.this, "Congratulations", Toast.LENGTH_LONG).show();
                    showSuccessDialog();
                }

                @Override
                public void onFailure(Call<SubmissionRequest> call, Throwable t) {
                    Log.d(TAG, "onFailure: Failed to submit");
                    //Toast.makeText(SubmissionActivity.this, "Oops", Toast.LENGTH_LONG).show();
                    showAbortedDialog();
                }
            });
        } else {
            Toast.makeText(this, "Fill the blanks", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_button:
                mDialog.cancel();
                makeSubmission();
                break;
            case R.id.negative_button:
                mDialog.cancel();
                showAbortedDialog();
                break;

        }

    }
}