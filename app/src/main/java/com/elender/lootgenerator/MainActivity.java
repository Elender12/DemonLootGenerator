package com.elender.lootgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.elender.lootgenerator.activities.CustomLoot;
import com.elender.lootgenerator.activities.LootGenerator;
import com.elender.lootgenerator.db.ItemDB;


/**
 * @author Elena Cirstea
 * GENERADOR DE TESOROS
 *
 * La aplicación permite generar un tesoro de una fuente determinada con una cantidad determinada según la preferencia del usuario
 *
 * Un botin se genera a partir de un conjunto 15 elementos, separados por colores que indican su calidad. El color que se genera para el botin es totalmente aleatorio
 *
 * El usuario puede generar su propio origen, siempre cuando introduza mínimo 3 elementos por cada color (15 elementos en total)
 *
 * */
public class MainActivity extends AppCompatActivity {

    Intent intent;
    ItemDB db;
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