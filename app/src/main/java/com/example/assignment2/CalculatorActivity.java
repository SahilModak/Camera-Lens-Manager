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
        Button calcButton = (Button) findViewById(R.id.calculator);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depthCalculator calc = new depthCalculator(Double.parseDouble(((EditText)findViewById(R.id.editTextNumber5)).getText().toString()),
                        currentLens,
                        Double.parseDouble(((EditText)findViewById(R.id.editTextNumber7)).getText().toString()),
                        Double.parseDouble(((EditText)findViewById(R.id.editTextNumber6)).getText().toString()));

                TextView hyperfocaldist = (TextView)findViewById(R.id.textView9);
                hyperfocaldist.setText(formatM(calc.hyperfocalDistance()));

                TextView farfocaldist = (TextView)findViewById(R.id.textView7);
                farfocaldist.setText(formatM(calc.farFocalPoint()));

                TextView nearfocaldist = (TextView)findViewById(R.id.textView6);
                nearfocaldist.setText(formatM(calc.nearFocalPoint()));

                TextView dof = (TextView)findViewById(R.id.textView8);
                dof.setText(formatM(calc.depthofField()));
            }
        });

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

        Button deleteButton = (Button) findViewById(R.id.deleteLensbutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.delete(positionSelected);
                Intent x = MainActivity.launchSwitchIntent(CalculatorActivity.this);
                startActivity(x);
            }
        });

        Button selectDiffLensButton = (Button) findViewById(R.id.selectDiffLens);
        selectDiffLensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = MainActivity.launchSwitchIntent(CalculatorActivity.this);
                startActivity(x);
            }
        });

    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        selectedLens = intent.getStringExtra(getSelected_lens);
        positionSelected = intent.getIntExtra(selectedPosition, 0);
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM) + "m";
    }

}