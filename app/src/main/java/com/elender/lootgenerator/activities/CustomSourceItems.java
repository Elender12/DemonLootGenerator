package com.elender.lootgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.elender.lootgenerator.R;
import com.elender.lootgenerator.adapters.CustomAdapter;
import com.elender.lootgenerator.db.ItemDB;
import com.elender.lootgenerator.db.LootItem;
import java.util.ArrayList;


/**
 * Clase que muestra los elementos de un origen determinado
 * */
public class CustomSourceItems extends AppCompatActivity implements CustomAdapter.ItemClickListener {

    private static final String TAG = "CustomSourceItems";
    ItemDB db;
    protected CustomAdapter mAdapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_source_items);
        tv = findViewById(R.id.tv_sourceName);
        db = ItemDB.getItemDataBase(this);

        String sourceName = null;
       //se recogen los datos desde la clase que invoca
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sourceName = bundle.getString("sourceName");
        }
        if(sourceName != null)
        {
            tv.setText(sourceName.toUpperCase());
            //llamada a la base de datos
            LootItem[] items = db.dao().showSourceItems(sourceName);
            //se verifica que haya datos
            if (items.length > 0) {
                ArrayList<String> data = new ArrayList<>();
                for (LootItem item : items) {
                    data.add("("+item.getLootColour() + ") " + item.getName());
                }
                RecyclerView recyclerView = findViewById(R.id.recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                mAdapter = new CustomAdapter(this, data);
                mAdapter.setClickListener(this);
                recyclerView.setAdapter(mAdapter);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        layoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
            } else {
                Toast.makeText(this, "No hay datos para esa fuente.", Toast.LENGTH_LONG).show();
                finish();
            }
        }else{
            Toast.makeText(this, "Hubo un error con el nombre del origen.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Has seleccionado " + mAdapter.getItem(position) + " en la línea " + position+1, Toast.LENGTH_SHORT).show();
    }


}