package com.example.android.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
        String[] p1_array = intent.getStringArrayExtra("p1_array");
        String[] p2_array = intent.getStringArrayExtra("p2_array");

        // Capture the layout's TextView and set the string as its text
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);


        textView1.setText(first_name);
        textView2.setText(second_name);

        if (p1_array == null) {
            Log.d(TAG, "ITS NULLLLLLLLLLLLLLL");
        }

        Log.d(TAG, p1_array[0]);
        Log.d(TAG, p2_array[0]);
    }
}
