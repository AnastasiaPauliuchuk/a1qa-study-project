package com.anp.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by KAPPA on 9/28/2017.
 */
public class MyDataResourcePropReader extends MyDataResourceReader {


    /*public void init(String resourceFilename) {
        super(resourceFilename);

    }*/

    public Map<String, String> readData(ArrayList<String> keys) {

        Map map = new HashMap<String, String>();
        Properties prop = new Properties();
        try {
            prop.load(this.inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String k : keys) {
            map.put(k, prop.getProperty(k));
        }
        return map;
    }

}