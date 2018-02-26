package com.skc.unit_6_asg;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textViewStatus;
    Button smsBtn;
    Button dialBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = (TextView) findViewById(R.id.textView);
        setupButtonClickEvents();
    }

    private void setupButtonClickEvents() {

        /**
         *   Set up button click event listener for the web info button for the first performance
         */
        smsBtn = (Button) findViewById(R.id.sms);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                textViewStatus.setText("Send text");

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:2183911863"));
                sendIntent.putExtra("sms_body", "Hi baba");

                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(sendIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activities.size() > 0;
                if(isIntentSafe){
                    startActivity(sendIntent);
                }
            }
        });

        dialBtn = (Button) findViewById(R.id.dial);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                textViewStatus.setText("Dial number");

                //by using ACTION_DIAL phone permissions aren't needed as the user must execute the call
                Intent sendIntent = new Intent(Intent.ACTION_DIAL);
                sendIntent.setData(Uri.parse("tel:2183911863"));

                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(sendIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activities.size() > 0;
                if(isIntentSafe){
                    startActivity(sendIntent);
                }
            }
        });

    }
}
