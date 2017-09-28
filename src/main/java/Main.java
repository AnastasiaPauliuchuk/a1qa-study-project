/**
 * Created by KAPPA on 9/26/2017.
 */
import com.anp.reader.MyDataResourcePropReader;
import com.anp.task.MyTask1;

import java.io.*;
import java.lang.*;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        MyTask1 myTask1 = new MyTask1("config1.txt", new ArrayList<String>(Arrays.asList("string1","string2")),new MyDataResourcePropReader());
        myTask1.performTask();

    }
}