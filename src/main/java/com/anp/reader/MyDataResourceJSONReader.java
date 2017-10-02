package com.anp.reader;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KAPPA on 9/28/2017.
 */
public class MyDataResourceJSONReader extends MyDataResourceReader {

    public MyDataResourceJSONReader(String resourceFilename) {
        this.resourceFilename = resourceFilename;
    }


    public Map<String, String> readData(ArrayList<String> keys) throws IOException {

        Map <String,String> map = new HashMap <String, String>();
        StringWriter sw = new StringWriter();
        IOUtils.copy(this.inputStream, sw, "UTF8");
        String jsonString = sw.toString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(jsonString,
                    new TypeReference<HashMap<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return map;
    }

}