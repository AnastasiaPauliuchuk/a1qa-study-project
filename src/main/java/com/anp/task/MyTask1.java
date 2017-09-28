package com.anp.task;

import com.anp.reader.MyDataReader;
import com.anp.reader.MyDataResourcePropReader;
import com.anp.writer.MyDataConsoleWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KAPPA on 9/27/2017.
 */
public class MyTask1 extends MyTask {

    public MyTask1(String resourceFilename, ArrayList<String> inputVarNames, MyDataReader dataReader)
    {
        //this.resourceFilename=resourceFilename;
        this.inputVarNames = inputVarNames;
        this.dataReader = dataReader;
            //new MyDataResourcePropReader();
        dataReader.init(resourceFilename);

        this.dataWriter = new MyDataConsoleWriter();
    }

    public Map<String, String> doTask(Map<String, String> vars) {
        String str1 = vars.get("string1");
        String str2 = vars.get("string2");

        // String[] sp1=;

        ArrayList<String> split1 = new ArrayList<>(Arrays.asList(str1.split(" ")));
        ArrayList<String> split2 = new ArrayList<>(Arrays.asList(str2.split(" ")));


        // String[] split21=str2.split("\\p{P}?[ \\t\\n\\r]+");
        //System.out.println(split1));
        for (String str : split1) {
            System.out.println(str);
        }
        for (String str : split2) {
            System.out.println(str);
        }
        split1.removeAll(split2);
        String finalStr = String.join(" ", split1);


        Map<String,String> result=new HashMap<String, String>() {{
            put("Результат", finalStr);
        }};
        return result;
    }
}
