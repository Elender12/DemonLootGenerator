package com.elender.lootgenerator.activities;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.elender.lootgenerator.R;
import com.elender.lootgenerator.db.LootItem;
import com.elender.lootgenerator.utils.GeneratorHelper;

public class GeneratedLootPopUp extends AppCompatActivity {

    private static final String TAG = "GeneratorPopUp";
    TextView txtColor, items;
    MediaPlayer mediaPlayer;
    int sound;
    ImageView img;
    Button backBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_loot_pop_up);
        ConstraintLayout constraintLayout = findViewById(R.id.layoutPopup);

        AnimationDrawable bckDrawable = (AnimationDrawable) constraintLayout.getBackground();
        img = findViewById(R.id.loot_image);
        items = findViewById(R.id.items_TV);
        txtColor = findViewById(R.id.txt_color);

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

        //query en la base de datos
        LootItem[] prize = GeneratorHelper.generateLoot(this, selectedSource, selectedQuantity);
        if (prize.length > 0) {
            String loot_colour =  prize[0].getLootColour();
            img.setImageResource(getResources().getIdentifier(loot_colour, "drawable", getPackageName()));
            setBckSound(loot_colour);
            setTxtColor(loot_colour);
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this, sound);
                mediaPlayer.start();
            }

            StringBuilder fullText= new StringBuilder();

            for (LootItem item: prize) {
                String text =  item.getName()+"\n";
                fullText.append(text);
            }
            items.setText(fullText);
        }else{
            Toast.makeText(this, "No hay suficientes elementos en la fuente para poder generar un botín. Considere añadir más. ", Toast.LENGTH_LONG).show();
            finish();
        }

      backBT = findViewById(R.id.backBT);
      backBT.setOnClickListener(view ->{
          mediaPlayer.release();
          mediaPlayer = null;
          finish();
              }
       );
        bckDrawable.setEnterFadeDuration(2000);
        bckDrawable.setExitFadeDuration(4000);
        bckDrawable.start();
    }

    private void setBckSound(String loot_colour){

        if(loot_colour.equalsIgnoreCase("blanco") || loot_colour.equalsIgnoreCase("verde") ){
            sound = getResources().getIdentifier("blanco","raw", getPackageName());
        }
       else  if(loot_colour.equalsIgnoreCase("azul") || loot_colour.equalsIgnoreCase("morado") ){
            sound = getResources().getIdentifier("azul","raw", getPackageName());
        }else {
            sound = getResources().getIdentifier("naranja","raw", getPackageName());
        }

    }
    private void setTxtColor(String loot_colour){
        txtColor.setText(loot_colour);
        if(loot_colour.equalsIgnoreCase("blanco")){
            txtColor.setTextColor(Color.WHITE);
        }
        else if(loot_colour.equalsIgnoreCase("verde")){
            txtColor.setTextColor(Color.rgb(8, 84, 36));
        }
        else if(loot_colour.equalsIgnoreCase("azul")){
            txtColor.setTextColor(Color.rgb(8, 56, 84));
        }
        else if(loot_colour.equalsIgnoreCase("morado")){
            txtColor.setTextColor(Color.rgb(156, 66, 245));
        }
        else if(loot_colour.equalsIgnoreCase("naranja")){
            txtColor.setTextColor(Color.rgb(245, 179, 66));
        }


    }
}