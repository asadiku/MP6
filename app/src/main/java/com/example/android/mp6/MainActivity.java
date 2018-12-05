package com.example.android.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE";
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button compare = findViewById(R.id.button_compare);
        compare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayComparisonActivity.class);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);
                String message2 = editText2.getText().toString();
                String message3 = editText3.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message2);
                intent.putExtra(EXTRA_MESSAGE2, message3);
                startActivity(intent);
            }
        });
    }
}
