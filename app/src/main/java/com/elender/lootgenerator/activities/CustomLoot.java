package com.elender.lootgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.ItemDB;
import com.elender.lootgenerator.db.LootItem;

public class CustomLoot extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Elementos del layout
    EditText itemName, sourceName;
    //Elemento para el manejo de bases de datos
    ItemDB db;
    //Spinner
    Spinner item_spinner;
    String itemType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loot);

        itemName= findViewById(R.id.item_name_ET);
        item_spinner= findViewById(R.id.spinner_type_items);
         sourceName= findViewById(R.id.source_name_ET);

        db = ItemDB.getItemDataBase(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.loot_colour_array, R.layout.item_selected);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
// Apply the adapter to the spinner
        item_spinner.setAdapter(adapter);
        item_spinner.setOnItemSelectedListener(this);
    }

    public void saveLootItem(View view){
        //TODO check source name
        LootItem lootItem = new LootItem(sourceName.getText().toString(), itemType, itemName.getText().toString());
        sourceName.setFocusable(false);
        sourceName.setEnabled(false);
        sourceName.setCursorVisible(false);
        sourceName.setKeyListener(null);
        sourceName.setBackgroundColor(Color.TRANSPARENT);
        Toast.makeText(this, "Se ha guardado "+itemName.getText().toString()+" "+sourceName.getText().toString(), Toast.LENGTH_LONG).show();
       // db.dao().insertItem(lootItem);
    }

    public void showCustomSourceItems(View view){
        Intent intent=new Intent(this, CustomSourceItems.class);
        //todo check also if is not empty?
        if(sourceName.getText().toString() != null && !sourceName.getText().toString().isEmpty() ){
            intent.putExtra("sourceName",sourceName.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(this, "Source name must have a value", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemType=  parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}