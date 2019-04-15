package com.example.viewsandviewgroups;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Demo_ViewsAndViewGroups
 *
 * Activity that demonstrates widgets and LinearLayout
 * @version 2019-04-14
 */
public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener {

    final static String TAG = "MainActivity sez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner takes a bit more setup in onCreate()
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.main_spn,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // This works because MainActivity implements the OnItemSelectedListener
        // interface
        // Note that you can also apply event handlers purely in code
        spinner.setOnItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Floating Action Button clicked.");
                Intent intent = new Intent(getApplicationContext(), RelativeLayoutActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * Use one click handler for all widgets ... because they're all
     * subclasses of View.
     *
     * @param view the widget that raised the event
     */
    public void onClick(View view) {
        Log.i(TAG, view.getClass().getSimpleName());

        // go to showPicker() code if picker button clicked.
        if (view.getId() == R.id.show_picker) {
            showPicker();
            return;
        }

        // Question: Does just one of this fires with each button click?
        // Or can  more than one if condition be true?
        if (view instanceof TextView) {
            Log.i(TAG, "Label clicked");
        }

        if (view instanceof Button) {
            Log.i(TAG, "Button clicked");
        }

        if (view instanceof CheckBox) {
            Log.i(TAG, "Checkbox clicked and its value is: " +
                    ((CheckBox) view).isChecked());
        }

        if (view instanceof EditText) {
            Log.i(TAG, "EditText clicked and its value is: " +
                    ((EditText) view).getText().toString());
        }

        if (view instanceof RadioButton) {
            Log.i(TAG, "RadioButton clicked and its value is: " +
                    ((RadioButton) view).isChecked());
        }

    }

    /**
     * Responds to onItemSelected event in spinner control.
     *
     * @param parent adapter view
     * @param view   the spinner control
     * @param pos    the position of the selected item
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // Docs don't tell this, but the item at the position returns the
        // base type of the data, usually string.
        String selectedItem = (String) parent.getItemAtPosition(pos);
        Log.i(TAG, "Spinner selected with value: " + selectedItem);
    }

    /**
     * Not sure why this is required, but it is ... ¯\_(ツ)_/¯
     *
     * @param parent
     */
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(TAG, "Nothing selected in spinner ...");
    }

    /**
     * Shows the DateTimeFragment with the DateTime picker
     * @see DatePickerFragment
     */
    private void showPicker() {
        Log.i(TAG, "Showing DateTime Fragment ...");
        // Do something with the date chosen by the user

        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String dateStr = String.format("%d/%d/%d", year, month + 1, day);
        Log.i(TAG, "Date picked is: " + dateStr);
    }
}
