package com.elender.lootgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.elender.lootgenerator.dbmanagement.ItemDB;
import com.elender.lootgenerator.dbmanagement.LootItem;

import java.util.ArrayList;

public class CustomSourceItems extends AppCompatActivity {

    ItemDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_source_items);

        db = ItemDB.getItemDataBase(this);
        Bundle bundle=getIntent().getExtras();
        String sourceName =bundle.getString("sourceName");
        LootItem[] items = db.dao().showSourceItems(sourceName);



        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < items.length; i++)
        {
            data.add(items[i].getLootColour() + " es " + items[i].getName());
        }
     //   ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, datos);
       // lista.setAdapter(adaptador);


    }



}