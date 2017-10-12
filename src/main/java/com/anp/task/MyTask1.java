package com.anp.task;

import com.anp.reader.MyDataReader;
import com.anp.writer.MyDataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anastasia Pauliuchuk on 9/27/2017.
 */
public class MyTask1 extends MyTask {

   public MyTask1( ArrayList<String> inputVarNames, MyDataReader dataReader, MyDataWriter dataWriter)
    {

        this.inputVarNames = inputVarNames;
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
      //  this.resourceFilename=resourceFilename;
    }

    public Map<String, String> doTask(Map <String,String> vars) {


        String str1=vars.get(this.inputVarNames.get(0));
        String str2=vars.get(this.inputVarNames.get(1));


        Pattern pSplit = Pattern.compile("\\W+");

        String[] wordsFromString2=pSplit.split(str2);
        String buffer = str1;
        for (String str : wordsFromString2) {
            Pattern p=Pattern.compile(str);
            Matcher m=p.matcher(buffer);
            buffer= m.replaceAll(" ");
        }

        String[] wordsFromString1=pSplit.split(buffer);
        String finalStr="";
        for(String word:wordsFromString1)
        {
            finalStr=finalStr+"\n"+word;
        }

        String finalStr1 = finalStr;
        Map<String,String> res=new HashMap<String, String>() {{
            put("Результат исключения слов второй строки из первой", finalStr1 );
        }};

        return res;
    }
}
