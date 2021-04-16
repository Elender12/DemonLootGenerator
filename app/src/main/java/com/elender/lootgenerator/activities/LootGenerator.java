package com.elender.lootgenerator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.ItemDB;
import java.util.List;

/**
 * Clase que recoge los valores del usuario para generar un botin
 *
 * */
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

        //spinners
        sourceSpinner = findViewById(R.id.spinner_sources);
        quantitySpinner = findViewById(R.id.spinner_quantity);

        List<String> sourceNames = db.dao().getSources();
        sourceSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sourceNames));

        // Se crea un array adapter para indicar la cantidad que quiere el usuario. Los valores de la cantidad se cargan desde res/values
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this, R.array.loot_options_array, R.layout.item_selected);
        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<>(this, R.layout.item_selected, sourceNames);


        quantityAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sourceAdapter.setDropDownViewResource(R.layout.spinner_dropdown);

        quantitySpinner.setAdapter(quantityAdapter);
        sourceSpinner.setAdapter(sourceAdapter);

        sourceSpinner.setOnItemSelectedListener(this);
        quantitySpinner.setOnItemSelectedListener(this);
    }


    public void generateLoot(View view) {
        //llamada a la ventana emergente para mostrar el botin
        Intent intent = new Intent(this, GeneratedLootPopUp.class);
        intent.putExtra("source", selectedSource);
        intent.putExtra("quantity", selectedQuantity);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner) parent;
        if (spin.getId() == R.id.spinner_sources) {
            selectedSource = parent.getItemAtPosition(position).toString();
        }
        if (spin.getId() == R.id.spinner_quantity) {
            selectedQuantity = position + 1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}