package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    private static final String getSelected_lens = "Selected Lens";

    private String selectedLens;

    public static Intent launchCalcIntent(Context c, String message){
        Intent intent = new Intent(c, CalculatorActivity.class);
        intent.putExtra(getSelected_lens, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        extractDataFromIntent();

        TextView lensName = (TextView)findViewById(R.id.textView11);
//        String selectedLens = lensName.getText().toString();
        lensName.setText(selectedLens);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        selectedLens = intent.getStringExtra(getSelected_lens);
    }
}