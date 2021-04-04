package com.elender.lootgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.LootItem;
import com.elender.lootgenerator.utils.GeneratorHelper;

public class GeneratedLootPopUp extends AppCompatActivity {

    private static final String TAG = "GeneratorPopUp";
    ImageView img, shine;
    TextView items;
    Button backBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_loot_pop_up);
        ConstraintLayout constraintLayout = findViewById(R.id.layoutPopup);
        AnimationDrawable bckDrawable = (AnimationDrawable) constraintLayout.getBackground();

        img = findViewById(R.id.loot_image);
        shine = findViewById(R.id.shine);
        items = findViewById(R.id.items_TV);

        String selectedSource = "";
        int selectedQuantity = 0;
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
             selectedSource =bundle.getString("source");
             selectedQuantity = bundle.getInt("quantity");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height= dm.heightPixels;
        getWindow().setLayout((int)(width* 0.9), ((int)(height*0.8)));

        LootItem[] prize = GeneratorHelper.generateLoot(this, selectedSource, selectedQuantity);
        String loot_colour =  prize[0].getLootColour();
        img.setImageResource(getResources().getIdentifier(loot_colour, "drawable", getPackageName()));




        StringBuilder fullText= new StringBuilder();

        for (LootItem item: prize) {
            String text =  item.getName()+"\n";
            fullText.append(text);
        }
        items.setText(fullText);


      backBT = findViewById(R.id.backBT);
      backBT.setOnClickListener(view -> finish());
        bckDrawable.setEnterFadeDuration(2000);
        bckDrawable.setExitFadeDuration(4000);
        bckDrawable.start();
    }
}