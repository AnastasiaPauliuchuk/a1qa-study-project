package com.anp.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KAPPA on 9/28/2017.
 */
public class MyDataResourceJSONReader extends MyDataResourceReader {

    /*public MyDataResourceJSONReader(String dataFilename) {
        super(dataFilename);
    }*/

    public Map<String, String> readData(ArrayList<String> keys) {

        Map map = new HashMap<String, String>();

      /*  Properties prop=new Properties();
        try {
            prop.load(this.inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String k:keys) {
            map.put(k,prop.getProperty(k));
        }*/
        return map;
    }

}