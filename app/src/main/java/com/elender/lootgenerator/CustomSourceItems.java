package com.elender.lootgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.elender.lootgenerator.dbmanagement.ItemDB;
import com.elender.lootgenerator.dbmanagement.LootItem;

import java.util.ArrayList;

public class CustomSourceItems extends AppCompatActivity  implements CustomAdapter.ItemClickListener {

    private static final String TAG = "CustomSourceItems";
    ItemDB db;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_source_items);

        db = ItemDB.getItemDataBase(this);
        Bundle bundle=getIntent().getExtras();
        String sourceName =bundle.getString("sourceName");
        LootItem[] items = db.dao().showSourceItems(sourceName);



        ArrayList<String> data = new ArrayList<>();
        mDataset = new String[items.length];
        for(int i = 0; i < items.length; i++)
        {
            data.add(items[i].getLootColour() + " es " + items[i].getName());
            mDataset[i] = items[i].getLootColour() + " es " + items[i].getName();
            Log.d(TAG, "onCreate: "+items[i].getLootColour() + " es " + items[i].getName());
        }
     //   ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, datos);
       // lista.setAdapter(adaptador);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        // set up the RecyclerView

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CustomAdapter(this, data);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }



}