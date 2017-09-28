package com.anp.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by KAPPA on 9/28/2017.
 */
public abstract class MyDataResourceReader implements MyDataReader {

    protected InputStream inputStream ;

    public void init(String dataFilename) {

        InputStream is = null;
        Class cls;
        cls = getClass();
        ClassLoader classLoader = cls.getClassLoader();
        try {

            is =classLoader.getResourceAsStream(dataFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.inputStream = is;
    }

    public abstract Map<String, String> readData(ArrayList<String> keys);
}