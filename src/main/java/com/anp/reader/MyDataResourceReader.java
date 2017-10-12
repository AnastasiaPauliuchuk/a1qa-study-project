package com.anp.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anastasia Pauliuchuk on 9/28/2017.
 */
public abstract class MyDataResourceReader implements MyDataReader {

    protected InputStream inputStream ;
    protected String resourceFilename;

    public MyDataResourceReader()
    {

    }

    public boolean init() {

        InputStream is = null;
        Class cls;
        cls=MyDataResourcePropReader.class;
        ClassLoader classLoader = cls.getClassLoader();
        try {

            is =classLoader.getResourceAsStream(this.resourceFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.inputStream = is;
        if(is==null) return false;
        return true;
    }

    public abstract Map<String, String> readData(ArrayList<String> keys) throws IOException;
}