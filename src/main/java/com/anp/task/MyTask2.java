package com.anp.task;

import com.anp.reader.MyDataReader;
import com.anp.writer.MyDataConsoleWriter;
import com.anp.writer.MyDataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KAPPA on 10/1/2017.
 */
public class MyTask2 extends MyTask {

   public MyTask2( ArrayList<String> inputVarNames, MyDataReader dataReader, MyDataWriter dataWriter)
    {

        this.inputVarNames = inputVarNames;
        this.dataReader = dataReader;
        dataReader.init();
        this.dataWriter = dataWriter;
    }

    public Map<String, String> doTask(Map<String, String> vars) {


        String sourceString = vars.get(this.inputVarNames.get(0));
        String word = vars.get(this.inputVarNames.get(1));



        Pattern p1=Pattern.compile("(\\W*|\\b)"+word+"\\W+",Pattern.CASE_INSENSITIVE );

        Pattern p2=Pattern.compile("\\p{Punct}");

        Matcher m = p1.matcher(sourceString);
        Integer count=0;
        while(m.find()) count++;



        String finalStr1 = sourceString;
        String finalCount=count.toString();
        Map<String, String> res = new HashMap<String, String>() {{

            put("Найдено слов", finalCount);
        }};


        Matcher m2 = p2.matcher(sourceString);
        count=0;
        while(m2.find()) count++;

        String finalCount2=count.toString();
        res.put("Найдено знаков препинания", finalCount2);

        return res;
    }
}


