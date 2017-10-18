package com.anp.writer;

import java.util.Map;

/**
 * Created by Anastasia Pauliuchuk on 9/28/2017.
 */
public class MyDataConsoleWriter implements MyDataWriter {

    public MyDataConsoleWriter(String header)
    {
        System.out.println(header);
    }

    public void writeData(Map<String, String> data) {
        //System.out.println(this.getClass().getSuperclass());
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Override
    public void writeData(String data) {
        System.out.println(data);
    }
}