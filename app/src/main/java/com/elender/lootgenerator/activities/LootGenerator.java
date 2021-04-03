package com.elender.lootgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.ItemDB;
import java.util.List;


public class LootGenerator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "LootGenerator";

    String selectedSource = "";
    int selectedQuantity = 0;
    ItemDB db;
    Spinner sourceSpinner, quantitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loot_generator);


        db = ItemDB.getItemDataBase(this);

        sourceSpinner = findViewById(R.id.spinner_sources);
        quantitySpinner = findViewById(R.id.spinner_quantity);



        String[] quantities = {"Poco", "Normal", "Mucho"};

        List<String> sourceNames = db.dao().getSources();
        sourceSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sourceNames));
        quantitySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, quantities));

        sourceSpinner.setOnItemSelectedListener(this);
        quantitySpinner.setOnItemSelectedListener(this);


    }


    public void generateLoot(View view) {

        Intent intent = new Intent(this, GeneratedLootPopUp.class);
        intent.putExtra("source", selectedSource);
        intent.putExtra("quantity", selectedQuantity);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spin = (Spinner) parent;
        if (spin.getId() == R.id.spinner_sources) {
       //     Toast.makeText(this, "se ha pulsado: " + position, Toast.LENGTH_SHORT).show();
            selectedSource = parent.getItemAtPosition(position).toString();
        }
        if (spin.getId() == R.id.spinner_quantity) {
         //   Toast.makeText(this, "se ha pulsado: " + position, Toast.LENGTH_SHORT).show();
            selectedQuantity = position+1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}