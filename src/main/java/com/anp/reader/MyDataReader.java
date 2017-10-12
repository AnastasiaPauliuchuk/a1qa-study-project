package com.anp.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anastasia Pauliuchuk on 9/27/2017.
 */
public interface MyDataReader {

    public Map<String, String> readData(ArrayList<String> keys) throws IOException;

    boolean init();
}




