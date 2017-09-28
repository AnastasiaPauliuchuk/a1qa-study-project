package com.anp.task;

import com.anp.reader.MyDataReader;
import com.anp.writer.MyDataWriter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by KAPPA on 9/27/2017.
 */
public abstract class MyTask {

    //private String resourceFilename;
    protected MyDataReader dataReader;
    protected MyDataWriter dataWriter;
    //private MyDataWriter dw;
    protected ArrayList<String> inputVarNames;

    // protected InputStream inputStream;


    public void performTask() {
        Map<String, String> inputVars = dataReader.readData(this.inputVarNames);
        dataWriter.writeData(doTask(inputVars));

    }

    ;

    public abstract Map<String, String> doTask(Map<String, String> vars);


}
/*
public class MyResultString
{
   private ArrayList<String> result;
   private String formatString;
   /*public MyResult(ArrayList<String> result, String format)
   {
       for (String s : this.result = result.addAll(result)) {

       }
       this.formatString=format;
       ;

   };*//*
   public String getResult()
   {
       return formatString;
   }

}
public interface MyData
{
    public readData();
}

public class MyTask1 implements MyTask {

    private String str1;
    private String str2;

   // private String resourceFilename="config.properties";
    public void getData(String resourceFilename)
    {
        Class cls;
        cls = Class.forName("MyTask1");

        // returns the ClassLoader object associated with this Class
        ClassLoader cLoader = cls.getClassLoader();

        System.out.println(cLoader.getClass());

        // finds resource with the given name
        Properties prop =new Properties();
        InputStream is= cLoader.getResourceAsStream(resourceFilename);
        try {
               getDataFromProp(is);
           }
        catch (IOException e) {
               e.printStackTrace();
           }
       /* try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str1 = prop.getProperty("string1");

        System.out.println(str1);
        // finds resource with the given name


        //FILE_NAME=ClassLoader.getSystemResources()
        //Files.lines(Paths.get(String.valueOf(url)), StandardCharsets.UTF_8).forEach(System.out::println);
    */
  /*  }


}

*/