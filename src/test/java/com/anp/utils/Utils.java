package com.anp.utils;

/**
 * Created by Anastasia Pauliuchuk on 10/22/2017.
 */
public class Utils {

    public static String reduceCtrlSyms(String str) {
        return str.replaceAll("[^a-zA-Zа-яА-Я\\d\\s]", "").replaceAll("\\p{Cntrl}", "");
    }
}

