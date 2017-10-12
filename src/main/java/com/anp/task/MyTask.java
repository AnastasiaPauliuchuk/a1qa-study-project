package com.anp.task;

import com.anp.reader.MyDataReader;
import com.anp.writer.MyDataWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anastasia Pauliuchuk on 9/27/2017.
 */
public abstract class MyTask {

    protected MyDataReader dataReader;
    protected MyDataWriter dataWriter;
    protected ArrayList<String> inputVarNames;

    public void performTask() {


        if(dataReader.init()) {
            try {
                Map<String, String> inputVars = dataReader.readData(this.inputVarNames);
                dataWriter.writeData(doTask(inputVars));
            }
            catch (IOException e)
            {
                dataWriter.writeData(new HashMap<String,String>(){{
                    put("Ошибка", "неверный формат данных" );
                }});
            }
        }
        else
            dataWriter.writeData(new HashMap<String,String>(){{
                    put("Ошибка", "невозможно прочитать источник данных" );
        }});


    }

    public abstract Map<String, String> doTask(Map<String, String> vars);


}
