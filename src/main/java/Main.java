/**
 * Created by KAPPA on 9/26/2017.
 */

import com.anp.reader.MyDataResourceJSONReader;
import com.anp.reader.MyDataResourcePropReader;
import com.anp.task.MyTask1;
import com.anp.task.MyTask2;
import com.anp.writer.MyDataConsoleWriter;

import java.lang.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {


       // MyDataResourcePropReader dataReader = new MyDataResourcePropReader("config1.txt");

        MyDataResourceJSONReader dataReader=new MyDataResourceJSONReader("config2.json");
        MyTask1 myTask1 = new MyTask1(new ArrayList<String>(Arrays.asList("string1", "string2")),
                dataReader,
                new MyDataConsoleWriter("Задание 1"));


        myTask1.performTask();

        MyDataResourcePropReader dataReader2 = new MyDataResourcePropReader("config1.txt");
        MyTask2 myTask2 = new MyTask2(new ArrayList<String>(Arrays.asList("string", "word")),
                dataReader2,
                new MyDataConsoleWriter("Задание 2")) {

        };

        myTask2.performTask();




        /*Map<String, String> map=new HashMap<>();
        map = JSONreader.readData(new ArrayList<String>(Arrays.asList("string", "word")));*/

    }
}