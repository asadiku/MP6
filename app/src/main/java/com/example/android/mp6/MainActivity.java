package com.example.android.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP6";

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    String first_name;
    String second_name;
    String console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(this);

        // Load the main layout for our activity
        setContentView(R.layout.activity_main);

        // Attach the handler to our UI button
        final Button compare = findViewById(R.id.button_compare);
        compare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayComparisonActivity.class);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);
                EditText textbox_console = (EditText) findViewById(R.id.textbox_console);
                first_name = editText2.getText().toString().toLowerCase();
                second_name = editText3.getText().toString().toLowerCase();
                console = textbox_console.getText().toString().toLowerCase(); // console must be "pc", "xbl", or "psn"


                intent.putExtra("first_name", first_name);
                intent.putExtra("second_name", second_name);
                intent.putExtra("console", console);


                Log.d(TAG, "Compare button clicked");
                startAPICall();
                startActivity(intent);
            }
        });
    }

    /**
     * Make an API call.
     */
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.fortnitetracker.com/v1/profile/" + console + "/" + first_name,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, "There was an error in your request");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("TRN-Api-Key", "e7aae4fa-c500-4948-9fec-2d8f179f6b74");
                    Log.d(TAG, params.toString());
                    return params;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
