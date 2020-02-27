package com.johnbrockway.rpgencylopedia.data;

import androidx.room.TypeConverter;
import java.util.ArrayList;
import java.util.List;

public class RPGTypeConverters {

    private static String delimiter = ",";

    @TypeConverter
    public static String stringFromListOfInteger(List<Integer> list) {
        String returnString = "";
        for (Integer e : list) {
            returnString += e.toString();
            returnString += delimiter;
        }
        return returnString.substring(0, returnString.length() - delimiter.length());
    }

    @TypeConverter
    public static List<Integer> listOfIntegerFromString(String string) {
        List<Integer> returnList = new ArrayList<Integer>();
        String[] stringArray = string.split(delimiter);
        for (int i = 0 ; i < stringArray.length ; i++) {
            try {
                int numberElement = Integer.parseInt(stringArray[i]);
                returnList.add(numberElement);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return returnList;
    }
}
