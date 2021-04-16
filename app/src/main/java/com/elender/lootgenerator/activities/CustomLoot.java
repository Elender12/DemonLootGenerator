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
import com.elender.lootgenerator.utils.Utils;


public class CustomLoot extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        //se crea una instancia de la base de datos
        db = ItemDB.getItemDataBase(this);

        // crear un adaptador para el spineer
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.loot_colour_array, R.layout.item_selected);
        // especificar el layout a utilizar
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        item_spinner.setAdapter(adapter);
        item_spinner.setOnItemSelectedListener(this);
    }

    /**
     * @param view:
     *
     */
    public void saveLootItem(View view) {
        //verifica que los dos valores introducidos por el usuario tengan contenido
        if (sourceName.getText().toString().length() == 0 || itemName.getText().toString().length() == 0) {
            Toast.makeText(this, "Todos los campos deben tener un valor.", Toast.LENGTH_LONG).show();

        } else {
            String source_name = Utils.prepareString(sourceName.getText().toString().toUpperCase());
            String item_name = Utils.prepareString(itemName.getText().toString());
            //verifico que el usuario no introduza caracteres especiales
            if (Utils.isValid(source_name) && Utils.isValid(item_name)) {
                //Instancio un elemento de tipo LootItem para guardarlo en la base de datos
                LootItem lootItem = new LootItem(source_name, itemType, item_name);
                //Deshabilito el input del nombre del origen para que el usuario pueda introducir de manera más fácil los elementos
                sourceName.setFocusable(false);
                sourceName.setEnabled(false);
                sourceName.setCursorVisible(false);
                sourceName.setKeyListener(null);
                sourceName.setBackgroundColor(Color.TRANSPARENT);
                //verifico que no haya otro elemento con el mismo nombre
                int count = db.dao().checkItem( lootItem.getName());
                if(count == 0){
                    //guardo el elemento en la base de datos
                    db.dao().insertItem(lootItem);
                    Toast.makeText(this, itemName.getText().toString().toUpperCase() + " se ha guardado en el origen " + sourceName.getText().toString().toUpperCase() + " con el color " + itemType, Toast.LENGTH_LONG).show();
                    //reinicio el valor del input del usuario
                    itemName.setText("");
                }else{
                    Toast.makeText(this, itemName.getText().toString() + " ya existe en la base de datos. ", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "No se permiten caracteres espciales.", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * @param view
     * Muestra los elementos de un origen a partir de un nombre
     */
    public void showCustomSourceItems(View view) {
        Intent intent = new Intent(this, CustomSourceItems.class);
        //verifico que no sea nulo o que no tenga valor
        if (sourceName.getText().toString() != null && !sourceName.getText().toString().isEmpty()) {
            intent.putExtra("sourceName", sourceName.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Debes introducir un nombre para el origen", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //recoger el color del elemento que se quiere añadir
        itemType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}