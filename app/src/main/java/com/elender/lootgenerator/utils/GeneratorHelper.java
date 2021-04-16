package com.elender.lootgenerator.utils;

import android.content.Context;
import android.util.Log;
import com.elender.lootgenerator.db.ItemDB;
import com.elender.lootgenerator.db.LootItem;
import java.util.Random;
/**
 * Lógica de probabilidades del generador de tesoros:
 * Cada tesoro tiene un color que representa su CALIDAD
 * El BLANCO representa un objeto de tipo COMÚN, mientras que el DORADO es la clase de objetos más rara y de mejor calidad.
 * Cada color tiene un PORCENTAJE de probabilidad de ser elegido.
 * */
public class GeneratorHelper {

    private static final String WHITE_LOOT = "blanco";
    private static final String GREEN_LOOT = "verde";
    private static final String BLUE_LOOT = "azul";
    private static final String PURPLE_LOOT = "morado";
    private static final String GOLDEN_LOOT = "dorado";

    private static final String TAG = "GeneratorHelper" ;

    /**
     *
     * @param context: contexto desde donde se llama el método
     * @param source: origen desde el cual se quiere el botin
     * @param quantity: cantidad de botin que se quiere
     * */
    public static LootItem[] generateLoot(Context context, String source, int quantity) {
        ItemDB db = ItemDB.getItemDataBase(context);
        Random rand = new Random();
        LootItem[] loot = null;
        //variable al azar que depende el tipo de objeto que va a salir

        int treasureValue = rand.nextInt(100) + 1;
        /* Una vez que se tiene el valor aleatorio, se hace una llamada a la base de datos en el caso correspondiente con el parámetro indicado */

        // objeto DORADO -> probabilidad de salir: 5%
        if (treasureValue > 0 && treasureValue < 6) {
            loot = db.dao().getLoot(source, GOLDEN_LOOT, quantity);
        }
        // objeto MORADO -> probabilidad de salir: 10%
        else if (treasureValue > 5 && treasureValue < 16) {
            loot = db.dao().getLoot(source, PURPLE_LOOT, quantity);
        }
        // objeto AZUL -> probabilidad de salir: 20%
        else if (treasureValue > 15 && treasureValue < 36) {
            loot = db.dao().getLoot(source, BLUE_LOOT, quantity);
        }
        // objeto VERDE -> probabilidad de salir: 25%
        else if (treasureValue > 35 && treasureValue < 61) {
            loot = db.dao().getLoot(source, GREEN_LOOT, quantity);
        }
        // objeto BLANCO -> probabilidad de salir: 40%
        else if (treasureValue > 60 && treasureValue < 101) {
            loot = db.dao().getLoot(source, WHITE_LOOT, quantity);
        }
        return loot;
    }
}
