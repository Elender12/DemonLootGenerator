package com.elender.lootgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.ItemDB;
import com.elender.lootgenerator.db.LootItem;

public class CustomLoot extends AppCompatActivity {

    //Elementos del layout
    EditText itemName, itemType, sourceName;
    //Elemento para el manejo de bases de datos
    ItemDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loot);

        itemName= findViewById(R.id.item_name_ET);
        itemType= findViewById(R.id.item_type_ET);
        sourceName= findViewById(R.id.source_name_ET);

        db = ItemDB.getItemDataBase(this);
    }

    public void saveLootItem(View view){
        //TODO check source name
        LootItem lootItem = new LootItem(sourceName.getText().toString(), itemType.getText().toString(), itemName.getText().toString());
        db.dao().insertItem(lootItem);
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

}