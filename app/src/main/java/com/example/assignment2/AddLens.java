package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddLens extends AppCompatActivity {

    public static Intent launchaddIntent(Context c){
        Intent intent = new Intent(c, AddLens.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cancelButton = (Button) findViewById(R.id.addLensCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MainActivity.launchSwitchIntent(AddLens.this);
                startActivity(i);
            }
        });

        Button addButton = (Button) findViewById(R.id.addLensAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LensManager manager = LensManager.getInstance();
                manager.add(new Lens(((EditText)findViewById(R.id.addLensMakeInput)).getText().toString(),
                        Double.parseDouble(((EditText)findViewById(R.id.addLensApertureInput)).getText().toString()),
                        Integer.parseInt(((EditText)findViewById(R.id.addLensFocalLengthinput)).getText().toString())));
                Intent i = MainActivity.launchSwitchIntent(AddLens.this);
                startActivity(i);
            }
        });
    }
}