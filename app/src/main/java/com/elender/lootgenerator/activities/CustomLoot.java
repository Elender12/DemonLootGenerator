package com.elender.lootgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.ItemDB;
import com.elender.lootgenerator.db.LootItem;
import com.elender.lootgenerator.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomLoot extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "CustomLoot";
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

        itemName = findViewById(R.id.item_name_ET);
        item_spinner = findViewById(R.id.spinner_type_items);
        sourceName = findViewById(R.id.source_name_ET);

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

    public void saveLootItem(View view) {
        if (sourceName.getText().toString().length() == 0 || itemName.getText().toString().length() == 0) {
            Toast.makeText(this, "Todos los campos deben tener un valor.", Toast.LENGTH_LONG).show();

        } else {
            String source_name = Utils.prepareString(sourceName.getText().toString().toUpperCase());
            String item_name = Utils.prepareString(itemName.getText().toString());
            if(Utils.isValid(source_name) && Utils.isValid(item_name)){
                Log.d(TAG, "saveLootItem: "+item_name+" "+source_name);
                LootItem lootItem = new LootItem(source_name, itemType, item_name);
                sourceName.setFocusable(false);
                sourceName.setEnabled(false);
                sourceName.setCursorVisible(false);
                sourceName.setKeyListener(null);
                sourceName.setBackgroundColor(Color.TRANSPARENT);
                Toast.makeText(this,   itemName.getText().toString() + " se ha guardado en la fuente " + sourceName.getText().toString() + " con el color " + itemType, Toast.LENGTH_LONG).show();
                db.dao().insertItem(lootItem);
                itemName.setText("");
            }else{
                Toast.makeText(this, "No se permiten caracteres espciales.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void showCustomSourceItems(View view) {
        Intent intent = new Intent(this, CustomSourceItems.class);
        //todo check also if is not empty?
        if (sourceName.getText().toString() != null && !sourceName.getText().toString().isEmpty()) {
            intent.putExtra("sourceName", sourceName.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Debes introducir un nombre para el origen", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}