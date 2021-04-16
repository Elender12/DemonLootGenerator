package com.elender.lootgenerator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase que recoge utilidades para manejar los datos tipo STRING
 * */
public class Utils {

    /**Método que recibe una palabra y la devuelve sin espacios, saltos de linea y tabulaciones
     * @param value: valor que introduce el usuario
     * @return       valor procesado
     * */
    public static String prepareString(String value){
        if(value != null){
           value =  value.trim();
           value =  value.replaceAll("^[\n\r\t]", "").replaceAll("[\n\r\t]$", "");
        }
    return value;
    }

    /**Devuelve verdadero/falso si el valor introducido cumple con el patrón de la expresión regular que se encarga de verificar caracteres especiales
     * @param value: valor que introduce el usuario
     * @return  booleano
     * */
    public static boolean isValid(String value){
        //solo permite letras, números y espacios
        String regex = "^+[a-zA-Z0-9á-üÁ-Ú\\u00f1\\u00d1 ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return  matcher.matches();
    }

}



