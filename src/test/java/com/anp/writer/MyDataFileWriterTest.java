package com.anp.writer;

import org.testng.annotations.Test;

/**
 * Created by Anastasia Pauliuchuk on 10/18/2017.
 */
public class MyDataFileWriterTest {

    private static final String OUTPUT_FILEPATH = "c://work//qa-test//test-project2//src//test//resources//log.txt";

    @Test
    public void testWriteData() throws Exception {
        MyDataFileWriter dataWriter = new MyDataFileWriter(OUTPUT_FILEPATH);
        dataWriter.writeData("test");
    }

}