package com.elender.lootgenerator.utils;

import android.content.Context;
import android.util.Log;
import com.elender.lootgenerator.db.ItemDB;
import com.elender.lootgenerator.db.LootItem;
import java.util.Random;

public class GeneratorHelper {

    private static final String WHITE_LOOT = "blanco";
    private static final String GREEN_LOOT = "verde";
    private static final String BLUE_LOOT = "azul";
    private static final String PURPLE_LOOT = "morado";
    private static final String GOLDEN_LOOT = "dorado";
    private static final String TAG = "GeneratorHelper" ;

    public static LootItem[] generateLoot(Context context, String source, int quantity) {
        ItemDB db = ItemDB.getItemDataBase(context);
        Random rand = new Random();
        LootItem[] loot = null;
        int treasureValue = rand.nextInt(100) + 1;
        Log.d(TAG, "generateLoot: treasureValue::::::"+treasureValue);
        // 5%
        if (treasureValue > 0 && treasureValue < 6) {
            loot = db.dao().getLoot(source, GOLDEN_LOOT, quantity);
            Log.d(TAG, "generateLoot: golden");
        }
        // 10%
        else if (treasureValue > 5 && treasureValue < 16) {

            loot = db.dao().getLoot(source, PURPLE_LOOT, quantity);
            Log.d(TAG, "generateLoot: PURPLE");
        }
        // 20%
        else if (treasureValue > 15 && treasureValue < 36) {
            loot = db.dao().getLoot(source, BLUE_LOOT, quantity);
            Log.d(TAG, "generateLoot: BLUE");;
        }
        // 25%
        else if (treasureValue > 35 && treasureValue < 61) {
            loot = db.dao().getLoot(source, GREEN_LOOT, quantity);
            Log.d(TAG, "generateLoot: GREEN");
        }
        // 40%
        else if (treasureValue > 60 && treasureValue < 101) {
            loot = db.dao().getLoot(source, WHITE_LOOT, quantity);
            Log.d(TAG, "generateLoot: WHITE");
        }
        return loot;
    }
}
