package jspHelper;

import java.util.*;

public class Countries {
    public static Map<String, String> createMapOfCountrues(String[] listOfCountryCodes){
        Map<String, String> mapOfCountrues = new LinkedHashMap<>();
        for(int i = 0; i < listOfCountryCodes.length; i++ ){
            Locale obj = new Locale("", listOfCountryCodes[i]);
            mapOfCountrues.put(listOfCountryCodes[i].toLowerCase(),obj.getDisplayCountry());
        }
        return mapOfCountrues;
    }
}
