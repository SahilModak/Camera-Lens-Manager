package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Intent launchSwitchIntent(Context d){
        Intent a = new Intent(d, MainActivity.class);
        return a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = AddLens.launchAddIntent(MainActivity.this, true);
                startActivity(b);
            }
        });

        displaylistView();
        registerClickCallback();
    }

    private void registerClickCallback() {
        ListView lst = (ListView) findViewById(R.id.lensDisplay);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String message = textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                Intent c = CalculatorActivity.launchCalcIntent(MainActivity.this, message, position);
                startActivity(c);
            }
        });
    }

    private void displaylistView() {
        LensManager manager = LensManager.getInstance();
        ArrayList<String> myitems = new ArrayList<>();
        for (int i = 0; i < manager.size(); i++) {
            myitems.add(manager.get(i).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.lenslistdisplay,
                myitems);

        ListView lst = (ListView) findViewById(R.id.lensDisplay);
        lst.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_CalculauteDof ||
                id == R.id.action_DeleteLens ||
                id == R.id.action_EditLens) {
            Toast.makeText(getApplicationContext(), "Select a lens!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.action_AddLens){
            Intent b = AddLens.launchAddIntent(MainActivity.this, true);
            startActivity(b);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}