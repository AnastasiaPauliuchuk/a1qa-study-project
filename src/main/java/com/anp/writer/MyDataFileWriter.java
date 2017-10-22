package com.anp.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Anastasia Pauliuchuk on 10/18/2017.
 */
public class MyDataFileWriter implements MyDataWriter {

    private String outputFilepath;

    public MyDataFileWriter(String outputFilepath) {
        this.outputFilepath = outputFilepath;
    }

    @Override
    public void writeData(Map<String, String> data) {

        try (FileWriter writer = new FileWriter(outputFilepath, true)) {

            for (Map.Entry<String, String> entry : data.entrySet()) {
                try {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.append('\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void writeData(String data) {


        try (FileWriter writer = new FileWriter(outputFilepath, true)) {
            writer.write(data);
            writer.append('\n');

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }
}
