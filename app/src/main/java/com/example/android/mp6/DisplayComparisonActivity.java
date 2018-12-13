package com.example.android.mp6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static com.example.android.mp6.MainActivity.MY_PREFS_NAME;

public class DisplayComparisonActivity extends AppCompatActivity {
    private static final String TAG = "MP6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comparison);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String first_name = intent.getStringExtra("first_name");
        String second_name = intent.getStringExtra("second_name");
        //String[] p1_array = intent.getStringArrayExtra("p1_array");
        //String[] p2_array = intent.getStringArrayExtra("p2_array");

        // Capture the layout's TextView and set the string as its text
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);


        textView1.setText(first_name);
        textView2.setText(second_name);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String kdRatio = prefs.getString("kdRatio", null);
        String winRatio = prefs.getString("winRatio", null);
        String totalKills = prefs.getString("totalKills", null);
        String totalWins = prefs.getString("totalWins", null);
        String totalMatches = prefs.getString("totalMatches", null);


        String kdRatio2 = prefs.getString("kdRatio2", null);
        String winRatio2 = prefs.getString("winRatio2", null);
        String totalKills2 = prefs.getString("totalKills2", null);
        String totalWins2 = prefs.getString("totalWins2", null);
        String totalMatches2 = prefs.getString("totalMatches2", null);

        /**
        Log.d(TAG, "Player 1 kdRatio " + kdRatio);
        Log.d(TAG, "Player 1 winRatio " + winRatio);
        Log.d(TAG, "Player 1 totalKills " + totalKills);
        Log.d(TAG, "Player 1 totalWins " + totalWins);
        Log.d(TAG, "Player 1 totalMatches " + totalMatches);

        Log.d(TAG, "Player 2 kdRatio  " + kdRatio2);
        Log.d(TAG, "Player 2 winRatio " + winRatio2);
        Log.d(TAG, "Player 2 totalKills " + totalKills2);
        Log.d(TAG, "Player 2 totalWins " + totalWins2);
        Log.d(TAG, "Player 2 totalMatches " + totalMatches2);
         **/

        textView3.setText("k/d ratio:     " + kdRatio + "\n \ntotal kills:     " + totalKills + "\n \nwin ratio:     " + winRatio + "\n \ntotal wins:     " + totalWins + "\n \ntotal matches:  " + totalMatches);
        textView4.setText("k/d ratio:     " + kdRatio2 + "\n \ntotal kills:     " + totalKills2 + "\n \nwin ratio:     " + winRatio2 + "\n \ntotal wins:     " + totalWins2 + "\n \ntotal matches:  " + totalMatches2);
    }
}
