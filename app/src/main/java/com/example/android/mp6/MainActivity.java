package com.example.android.mp6;

import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.os.Handler;
import android.content.Intent;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP6";
    public static final String PREFS_NAME = "MyPrefsFile";

    public String[] outputArray1 = new String[5];
    public String[] outputArray2 = new String[5];

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    public String first_name;
    public String second_name;
    public String console;

    public static final String MY_PREFS_NAME = "MyPrefsFile";


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
                //startAPICall1();
                //startAPICall2();

                if (outputArray1 == null) {
                    Log.d(TAG, "why is this nullll??? #2");
                }
                Intent intent = new Intent(v.getContext(), DisplayComparisonActivity.class);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);
                EditText textbox_console = (EditText) findViewById(R.id.textbox_console);
                first_name = editText2.getText().toString().toLowerCase();
                second_name = editText3.getText().toString().toLowerCase();
                console = textbox_console.getText().toString().toLowerCase(); // console must be "pc", "xbl", or "psn"


                intent.putExtra("first_name", editText2.getText().toString());
                intent.putExtra("second_name", editText3.getText().toString());
                intent.putExtra("console", textbox_console.getText().toString());
                intent.putExtra("p1_array", outputArray1);
                intent.putExtra("p2_array", outputArray2);


                Log.d(TAG, "Compare button clicked");
                startAPICall1();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startAPICall2();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }

    /**
     * Make an API call.
     */
    void startAPICall1() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.fortnitetracker.com/v1/profile/" + console + "/" + first_name,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                //Log.d(TAG, response.toString(4));
                                JSONObject statsObject = response.getJSONObject("stats");
                                JSONObject p2Object = statsObject.getJSONObject("p2");
                                JSONObject kdObject = p2Object.getJSONObject("kd");
                                JSONObject winRatioObject = p2Object.getJSONObject("winRatio");
                                JSONObject winsObject = p2Object.getJSONObject("top1");
                                JSONObject matchesObject = p2Object.getJSONObject("matches");
                                JSONObject killsObject = p2Object.getJSONObject("kills");
                                String kdRatio = kdObject.getString("value");
                                String winRatio = winRatioObject.getString("value");
                                String totalKills = killsObject.getString("value");
                                String totalWins = winsObject.getString("value");
                                String totalMatches = matchesObject.getString("value");
                                outputArray1 = new String[] {kdRatio, winRatio, totalKills, totalWins, totalWins, totalMatches};

                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("kdRatio", kdRatio);
                                editor.putString("winRatio", winRatio);
                                editor.putString("totalKills", totalKills);
                                editor.putString("totalWins", totalWins);
                                editor.putString("totalMatches", totalMatches);
                                editor.apply();

                                if (outputArray1 == null) {
                                    Log.d(TAG, "why is this nullll???");
                                }


                            } catch (JSONException e) {
                                Log.d(TAG, "Json broke");
                            }
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

    /**
     * Make an API call.
     */
    void startAPICall2() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.fortnitetracker.com/v1/profile/" + console + "/" + second_name,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                //Log.d(TAG, response.toString(4));
                                JSONObject statsObject = response.getJSONObject("stats");
                                JSONObject p2Object = statsObject.getJSONObject("p2");
                                JSONObject kdObject = p2Object.getJSONObject("kd");
                                JSONObject winRatioObject = p2Object.getJSONObject("winRatio");
                                JSONObject winsObject = p2Object.getJSONObject("top1");
                                JSONObject matchesObject = p2Object.getJSONObject("matches");
                                JSONObject killsObject = p2Object.getJSONObject("kills");
                                String kdRatio = kdObject.getString("value");
                                String winRatio = winRatioObject.getString("value");
                                String totalKills = killsObject.getString("value");
                                String totalWins = winsObject.getString("value");
                                String totalMatches = matchesObject.getString("value");
                                String[] returnArray = {kdRatio, winRatio, totalKills, totalWins, totalWins, totalMatches};
                                outputArray2 = returnArray;

                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("kdRatio2", kdRatio);
                                editor.putString("winRatio2", winRatio);
                                editor.putString("totalKills2", totalKills);
                                editor.putString("totalWins2", totalWins);
                                editor.putString("totalMatches2", totalMatches);
                                editor.apply();


                            } catch (JSONException e) {
                                Log.d(TAG, "Json broke");
                            }
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
