package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import java.util.List;

/**
 * This is a utility class which contains general utility functions.
 */

public class Utils {
    private static String strSeparator = " , ";

    /**
     * This method takes a list of strings and returns all the list elements in the form of a comma separated string.
     */
    public static String convertArrayListToString(List<String> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i);
            // Do not append comma at the end of last element
            if (i < list.size() - 1) {
                str = str + strSeparator;
            }
        }
        return str;
    }
}
