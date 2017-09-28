package com.anp.writer;

import java.util.Map;

/**
 * Created by KAPPA on 9/28/2017.
 */
public class MyDataConsoleWriter implements MyDataWriter {

    public void writeData(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}