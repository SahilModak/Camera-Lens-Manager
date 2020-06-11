package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLens extends AppCompatActivity {
    private int pos = -1;

    public static Intent launchAddIntent(Context c){
        Intent intent = new Intent(c, AddLens.class);
        return intent;
    }

    public static Intent launchEditIntent(Context c, int position, boolean val){
        Intent intent = new Intent(c, AddLens.class);
        intent.putExtra("position of lens in manager - ", position);
        intent.putExtra("checker - ", val);
        return intent;
    }


    private void extractdatafromEditIntent(){
        Intent intent = getIntent();
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
        if(pos != -5) {
            int poscopy = pos;
            EditText lensMake = (EditText) findViewById(R.id.addLensMakeInput);
            lensMake.setText(x.get(pos).getMake());

            EditText lensFocal = (EditText) findViewById(R.id.addLensFocalLengthInput);
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
                    Boolean check = true;
                    if((((EditText) findViewById(R.id.addLensMakeInput)).getText().toString()).length() == 0){
                        check = false;
                        EditText lensMake = (EditText) findViewById(R.id.addLensMakeInput);
                        lensMake.setError("Please enter a Make for your Lens, example: 'Canon'");
                    }
                    if((((EditText) findViewById(R.id.addLensApertureInput)).getText().toString().length() == 0) ||
                            ((Double.parseDouble(((EditText) findViewById(R.id.addLensApertureInput)).getText().toString()))) <= 1.4) {
                        check = false;
                        EditText lensAperture = (EditText) findViewById(R.id.addLensApertureInput);
                        lensAperture.setError("Please enter a valid aperture for your Lens, " +
                                "example: 1.5 for 1.5mm, valid apertures are " +
                                "those greater than or equal to 1.4mm");
                    }
                    if ((((EditText) findViewById(R.id.addLensFocalLengthInput)).getText().toString().length() == 0) ||
                            ((Integer.parseInt(((EditText) findViewById(R.id.addLensFocalLengthInput)).getText().toString())) < 0)){
                        check = false;
                        EditText lensfocal = (EditText) findViewById(R.id.addLensFocalLengthInput);
                        lensfocal.setError("Please enter a valid focal length for your Lens, " +
                                "example: 90 for 90mm, focal lengths are greater than 0");
                    }
                    if(check) {
                        x.edit(poscopy,
                                ((EditText) findViewById(R.id.addLensMakeInput)).getText().toString(),
                                Double.parseDouble(((EditText) findViewById(R.id.addLensApertureInput)).getText().toString()),
                                Integer.parseInt(((EditText) findViewById(R.id.addLensFocalLengthInput)).getText().toString()));
                        Intent i = MainActivity.launchSwitchIntent(AddLens.this);
                        startActivity(i);
                    }
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
                    Boolean check = true;
                    if((((EditText) findViewById(R.id.addLensMakeInput)).getText().toString()).length() == 0){
                        check = false;
                        EditText lensMake = (EditText) findViewById(R.id.addLensMakeInput);
                        lensMake.setError("Please enter a Make for your Lens, example: 'Canon'");
                    }
                    if((((EditText) findViewById(R.id.addLensApertureInput)).getText().toString().length() == 0) ||
                            ((Double.parseDouble(((EditText) findViewById(R.id.addLensApertureInput)).getText().toString()))) <= 1.4) {
                        check = false;
                        EditText lensAperture = (EditText) findViewById(R.id.addLensApertureInput);
                        lensAperture.setError("Please enter a valid aperture for your Lens, " +
                                "example: 1.5 for 1.5mm, valid apertures are " +
                                "those greater than or equal to 1.4mm");
                    }
                    if ((((EditText) findViewById(R.id.addLensFocalLengthInput)).getText().toString().length() == 0) ||
                            ((Integer.parseInt(((EditText) findViewById(R.id.addLensFocalLengthInput)).getText().toString())) < 0)){
                        check = false;
                        EditText lensfocal = (EditText) findViewById(R.id.addLensFocalLengthInput);
                        lensfocal.setError("Please enter a valid focal length for your Lens, " +
                                "example: 90 for 90mm, focal lengths are greater than 0");
                    }
                    if(check) {
                        LensManager x = LensManager.getInstance();
                        x.add(new Lens(((EditText) findViewById(R.id.addLensMakeInput)).getText().toString(),
                                Double.parseDouble(((EditText) findViewById(R.id.addLensApertureInput)).getText().toString()),
                                Integer.parseInt(((EditText) findViewById(R.id.addLensFocalLengthInput)).getText().toString())));
                        Intent i = MainActivity.launchSwitchIntent(AddLens.this);
                        startActivity(i);
                    }
                }
            });
        }
    }
}