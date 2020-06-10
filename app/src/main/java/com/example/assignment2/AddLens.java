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
import android.widget.TextView;
import android.widget.Toast;

public class AddLens extends AppCompatActivity {

    private boolean val;
    private int pos = -1;

    public static Intent launchAddIntent(Context c, boolean val){
        Intent intent = new Intent(c, AddLens.class);
        intent.putExtra("boolabc - ", val);
        return intent;
    }

    public static Intent launchEditIntent(Context c, int position, boolean val){
        Intent intent = new Intent(c, AddLens.class);
        intent.putExtra("position of lens in manager - ", position);
        intent.putExtra("checker - ", val);
        return intent;
    }

    private void extractdatafromAddIntent(){
        Intent intent = getIntent();
        val = intent.getBooleanExtra("boolabc - ", true);

    }

    private void extractdatafromEditIntent(){
        Intent intent = getIntent();
        val = intent.getBooleanExtra("checker - ", true);
        pos = intent.getIntExtra("position of lens in manager - ", -5);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LensManager x = LensManager.getInstance();
        extractdatafromEditIntent();
        extractdatafromAddIntent();
        if(pos != -5) {
            int poscopy = pos;
            EditText lensMake = (EditText) findViewById(R.id.addLensMakeInput);
            lensMake.setText(x.get(pos).getMake());

            EditText lensFocal = (EditText) findViewById(R.id.addLensFocalLengthinput);
            lensFocal.setText(String.valueOf(x.get(pos).getFocal_Length()));

            EditText lensAp = (EditText) findViewById(R.id.addLensApertureInput);
            lensAp.setText(String.valueOf(x.get(pos).getMax_Aperture()));
            pos = -5;
            Button cancelButton = (Button) findViewById(R.id.addLensCancel);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = CalculatorActivity.launchSwitchIntent(AddLens.this);
                    startActivity(i);
                }
            });

            Button addButton = (Button) findViewById(R.id.addLensAdd);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x.edit(poscopy,
                            ((EditText) findViewById(R.id.addLensMakeInput)).getText().toString(),
                            Double.parseDouble(((EditText) findViewById(R.id.addLensApertureInput)).getText().toString()),
                            Integer.parseInt(((EditText) findViewById(R.id.addLensFocalLengthinput)).getText().toString()));
                    Intent i = MainActivity.launchSwitchIntent(AddLens.this);
                    startActivity(i);
                }
            });
        }
        else {
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
                    manager.add(new Lens(((EditText) findViewById(R.id.addLensMakeInput)).getText().toString(),
                            Double.parseDouble(((EditText) findViewById(R.id.addLensApertureInput)).getText().toString()),
                            Integer.parseInt(((EditText) findViewById(R.id.addLensFocalLengthinput)).getText().toString())));
                    Intent i = MainActivity.launchSwitchIntent(AddLens.this);
                    startActivity(i);
                }
            });
        }
    }
}