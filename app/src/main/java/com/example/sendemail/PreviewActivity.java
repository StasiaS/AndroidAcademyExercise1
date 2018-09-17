package com.example.sendemail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {
    private static final String KEY_MESSAGE = "KEY_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        final String message = getIntent().getStringExtra(KEY_MESSAGE);
        TextView tvPreview = findViewById(R.id.tvPreview);
        tvPreview.setText(message);
        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmailApp(message);
            }
        });
    }

    public static void start(Activity activity, String message) {
        Intent intent = new Intent(activity, PreviewActivity.class);
        intent.putExtra(KEY_MESSAGE, message);
        activity.startActivity(intent);
    }

    private void openEmailApp(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.base_email));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.android_academy_exercise_1));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, getString(R.string.send_email)));
        } else {
            Toast.makeText(this, getString(R.string.no_email_app_found), Toast.LENGTH_LONG).show();
        }
    }
}
