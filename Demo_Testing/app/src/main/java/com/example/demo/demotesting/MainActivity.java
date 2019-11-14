package com.example.demo.demotesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final static String TAG = "MAIN_ACTIVITY";

    final static HashMap<String, Double> rates = new HashMap<>();

    String[] tipTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.tipTypes = getResources().getStringArray(R.array.tip_types);

        rates.put(tipTypes[0], 0.20);
        rates.put(tipTypes[1], 0.10);
        rates.put(tipTypes[2], 0.10);

        TipProfile profile = new TipProfile(this);

        Log.i(TAG, "I'm informational!");
        Log.e(TAG, "I'm erroneous!");
        Log.wtf(TAG, "????");
        Log.v(TAG, "Very fine?");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        Spinner spinner = (Spinner)findViewById(R.id.spn_svc_type);
        Spinner spnQuality = (Spinner)findViewById(R.id.spn_quality);
        EditText editText = (EditText)findViewById(R.id.txtbx_svc_amt);
        TextView output = (TextView)findViewById(R.id.tip_output);

        String serviceType = spinner.getSelectedItem().toString();
        String serviceAmountStr = editText.getText().toString();
        String serviceQualityStr =  spnQuality.getSelectedItem().toString();

        if (!serviceAmountStr.isEmpty() &&
                !serviceType.isEmpty() &&
                !serviceQualityStr.isEmpty()) {

            Double serviceAmount = Double.parseDouble(serviceAmountStr);

            Log.i(TAG, serviceType);
            Log.i(TAG, Double.toString(serviceAmount));

            Double rate = rates.get(spinner.getSelectedItem().toString());

            TipProfile profile = new TipProfile(serviceType, rate);

            double serviceQuality = (serviceQualityStr.contains("Good")) ?
                    TipProfile.SERVICE_GOOD : (serviceQualityStr.contains("Mediocre")) ?
                    TipProfile.SERVICE_MEH : TipProfile.SERVICE_BAD;

            Double tipAmount =
                    profile.getTipAmount(serviceAmount, serviceQuality);

            output.setText(String.format("$%.02f", tipAmount));
        } else {
            Toast toast = Toast.makeText(this,
                        "Check your inputs", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
