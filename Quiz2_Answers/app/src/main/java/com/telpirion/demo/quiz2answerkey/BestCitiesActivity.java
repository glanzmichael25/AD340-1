package com.telpirion.demo.quiz2answerkey;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BestCitiesActivity extends AppCompatActivity {

    private final static String TAG = "BestCitiesActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_cities);

        String bestCitiesJSON = getJSONResourceString();
        Log.i(TAG, bestCitiesJSON);

        String bestCities = deserializeCities(bestCitiesJSON);
        TextView textView = findViewById(R.id.lb_best_cities);
        textView.setText(bestCities);
    }

    /*
    +1 point for having the method signature correct.

    Deducations:
    -1: Method must be `private String deserializeCities(String input)`
    -0.5: Method must take one argument, a string.
    */
    private String deserializeCities(String input) {
        String result = "";

        /*
        +1 point for putting the operation in try/catch with JSONException

        Deductions:
        -1: JSON operations using JSONObject must be in try/catch.
         */
        try {
            // NOTE:
            // (Using Gson for this question is worth partial credit.)

            /*
            +1 point for getting the root & arrays out of the JSON data

            Deductions:
            -1: Create a new JSONObject by passing the input string into the JSONObject constructor.
            -0.5: Must create a JSONArray object by calling JSONObject.getJSONArray().
             */
            JSONObject root = new JSONObject(input);
            JSONArray cities = root.getJSONArray("bestUSCities");

            /*
            +1 point for iterating over the cities

            Deductions:
            -1: Method must iterate over JSONArray of city data.
            -1: Method must select the city data.
            -0.5: Method must build a string
            -0.5: Method must use JSONObject.getString("cityName") and JSONObject.getString("state").
             */
            for (int i = 0; i < cities.length(); i++) {
                JSONObject city = cities.getJSONObject(i);
                String name = city.getString("cityName");
                String state = city.getString("state");

                result += String.format("%s, %s\n", name, state);

            }

        } catch (JSONException ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        /*
         +1 point for returning the result.

         Deductions:
         -1: Method must have a return statement with a string.
         -0.5: Method returns a string.
          */
        return result;
    }


    private String getJSONResourceString() {
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        BufferedReader reader = null;
        String results = "";

        try {
            InputStream inputStream = resources.openRawResource(R.raw.cities);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            results = builder.toString();
        } catch (Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return results;
    }

}
