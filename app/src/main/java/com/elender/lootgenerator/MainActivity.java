package com.elender.lootgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.elender.lootgenerator.activities.CustomLoot;
import com.elender.lootgenerator.activities.LootGenerator;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toGenerateLoot(View view){
        intent = new Intent(this, LootGenerator.class);
        startActivity(intent);
    }

    public void toCreateCustomLoot(View view){
        intent = new Intent(this, CustomLoot.class);
        startActivity(intent);
    }
}