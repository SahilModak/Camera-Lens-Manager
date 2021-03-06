package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;
import com.example.assignment2.model.depthCalculator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {
    private static final String getSelected_lens = "Selected Lens";
    private static final String selectedPosition = "Selected Position";

    private String selectedLens;
    private int positionSelected;

    // Intent to switch to CalculatorActivity page
    public static Intent launchCalcIntent(Context c, String message, int position){
        Intent intent = new Intent(c, CalculatorActivity.class);
        intent.putExtra(getSelected_lens, message);
        intent.putExtra(selectedPosition, position);
        return intent;
    }

    public static Intent launchSwitchIntent(Context c) {
        Intent a = new Intent(c, CalculatorActivity.class);
        return a;
    }

    // Code to run on entering the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        extractDataFromIntent();

        TextView lensName = (TextView)findViewById(R.id.textView11);
        lensName.setText(selectedLens);
        LensManager manager = LensManager.getInstance();
        Lens currentLens = manager.get(positionSelected);

        EditText COC = (EditText) findViewById(R.id.editTextNumber5);
        EditText distToSubj = (EditText) findViewById(R.id.editTextNumber6);
        EditText selectedAperture = (EditText) findViewById(R.id.editTextNumber7);

        //  TextWatcher to register and recalculate on change for the fields
        TextWatcher watch = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView hyperfocaldist = (TextView)findViewById(R.id.textView9);
                TextView farfocaldist = (TextView)findViewById(R.id.textView7);
                TextView nearfocaldist = (TextView)findViewById(R.id.textView6);
                TextView dof = (TextView)findViewById(R.id.textView8);

                // Error Checking
                if (distToSubj.getText().toString().length() == 0||
                        Double.parseDouble(distToSubj.getText().toString()) <= 0){
                    hyperfocaldist.setText("");
                    farfocaldist.setText("");
                    nearfocaldist.setText("");
                    dof.setText("");
                    distToSubj.setError(getString(R.string.error5));
                    return;
                };
                if (selectedAperture.getText().toString().length() == 0 ||
                        Double.parseDouble(selectedAperture.getText().toString()) <= 0 ||
                        Double.parseDouble(selectedAperture.getText().toString()) <= manager.get(positionSelected).getMax_Aperture()) {
                    hyperfocaldist.setText("");
                    farfocaldist.setText("");
                    nearfocaldist.setText("");
                    dof.setText("");
                    selectedAperture.setError(getString(R.string.error4));
                    return;
                };
                if((COC.getText().toString().length() == 0 ||
                        Double.parseDouble(COC.getText().toString()) <= 0)){
                    hyperfocaldist.setText("");
                    farfocaldist.setText("");
                    nearfocaldist.setText("");
                    dof.setText("");
                    COC.setError(getString(R.string.error6));
                    return;
                };

                // Creates depthCalculator object with the registered values
                depthCalculator calc = new depthCalculator(Double.parseDouble(((EditText)findViewById(R.id.editTextNumber5)).getText().toString()),
                        currentLens,
                        Double.parseDouble(((EditText)findViewById(R.id.editTextNumber7)).getText().toString()),
                        Double.parseDouble(((EditText)findViewById(R.id.editTextNumber6)).getText().toString()));

                // Calculating all the following values
                hyperfocaldist.setText(formatM(calc.hyperfocalDistance()));

                farfocaldist.setText(formatM(calc.farFocalPoint()));

                nearfocaldist.setText(formatM(calc.nearFocalPoint()));

                dof.setText(formatM(calc.depthofField()));
            }
        };

        // Text change listener for auto-recalculate
        COC.addTextChangedListener(watch);
        distToSubj.addTextChangedListener(watch);
        selectedAperture.addTextChangedListener(watch);

        // Edit button and its functionality
        Button editButton = (Button) findViewById(R.id.editLensbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = AddLens.launchEditIntent(CalculatorActivity.this,
                        positionSelected,
                        true);
                startActivity(b);
            }
        });

        // Delete button and its functionality
        Button deleteButton = (Button) findViewById(R.id.deleteLensbutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.delete(positionSelected);
                Intent x = MainActivity.launchSwitchIntent(CalculatorActivity.this);
                startActivity(x);
            }
        });

        // Change selected lens button and its functionality
        Button selectDiffLensButton = (Button) findViewById(R.id.selectDiffLens);
        selectDiffLensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = MainActivity.launchSwitchIntent(CalculatorActivity.this);
                startActivity(x);
            }
        });

    }

    // Grabbing the extra data sent through the Intent across pages
    private void extractDataFromIntent() {
        Intent intent = getIntent();
        selectedLens = intent.getStringExtra(getSelected_lens);
        positionSelected = intent.getIntExtra(selectedPosition, 0);
    }

    // Function to convert to 2dp
    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM) + "m";
    }

}