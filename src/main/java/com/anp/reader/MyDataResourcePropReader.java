package com.anp.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Anastasia Pauliuchuk on 9/28/2017.
 */
public class MyDataResourcePropReader extends MyDataResourceReader {

    private Properties prop;

    public MyDataResourcePropReader(String resourceFilename) {
        this.resourceFilename = resourceFilename;
        this.init();
        prop = new Properties();
        try {
            prop.load(this.inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Map<String, String> readData(ArrayList<String> keys) throws IOException {


        Map map = new HashMap<String, String>();

        for (String k : keys) {
            map.put(k, prop.getProperty(k));
        }
        return map;
    }

    public String readProperty(String key) {
        return prop.getProperty(key);
    }

}