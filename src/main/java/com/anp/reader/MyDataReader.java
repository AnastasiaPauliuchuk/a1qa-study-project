package com.anp.reader;

import java.io.IOException;
import java.util.*;

/**
 * Created by KAPPA on 9/27/2017.
 */
public interface MyDataReader {

    public Map<String, String> readData(ArrayList<String> keys) throws IOException;

    boolean init();
}




