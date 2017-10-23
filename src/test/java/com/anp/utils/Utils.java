package com.anp.utils;

import java.util.Locale;

/**
 * @author Anastasia Pauliuchuk
 * created 10/19/2017
 */

public class Utils {

    /**
     * Remove invisible and control symbols
     *
     * @param sourceString
     * @return result string
     */
    public static String removeCtrlSyms(String sourceString) {
        return sourceString.replaceAll("[^a-zA-Zа-яА-Я\\d\\s]", "").replaceAll("\\p{Cntrl}", "");
    }

    /**
     * Create case-insensitive regexp from Unicode string
     *
     * @param sourceString
     * @return regexp
     */
    public static String buildRegExp(String sourceString) {
        String c = sourceString.substring(0, 1);
        String upC = c.toUpperCase(new Locale("ru"));
        sourceString = sourceString.replaceAll(" ", ")(.*)(");
        return "([" + c + upC + "]" + sourceString.substring(1, sourceString.length()) + ")";
    }
}

