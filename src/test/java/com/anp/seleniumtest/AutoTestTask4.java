package com.anp.seleniumtest;

import com.anp.reader.MyDataResourcePropReader;
import com.anp.seleniumtest.pages.HomePage;
import com.anp.seleniumtest.pages.JobsPage;
import com.anp.seleniumtest.pages.SearchJobsPage;
import com.anp.writer.MyDataFileWriter;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.anp.utils.Utils.buildRegExp;

/**
 * Jobs page www.jobs.tut.by
 *
 * @author Anastasia Pauliuchuk
 * @since 10/19/2017
 */
public class AutoTestTask4 {

    private static final String TEST_DATA_FILENAME = "task4.properties";

    private static final String ouputFormat = "%s | %s";
    private static final String dateOutputFormat = "dd/MM/yyyy HH:mm:ss";

    private String searchString;
    private MyDataFileWriter dataWriter;

    @BeforeTest
    @Parameters({"browser", "searchString"})
    public void setUp(String browser, String searchString) {

        MyDataResourcePropReader dataReader = new MyDataResourcePropReader(TEST_DATA_FILENAME);
        dataWriter = new MyDataFileWriter(dataReader.readProperty("outputFilename"));

        this.searchString = searchString;

        Driver.init(browser);
        Driver.getInstance().getWebDriver().navigate().to(dataReader.readProperty("testURL"));
    }

    @Test
    public void testTutBy() {

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageLoaded());

        homePage.goJobsPage();
        JobsPage jobsPage = new JobsPage();
        Assert.assertTrue(jobsPage.isPageLoaded());


        jobsPage.doSearch(searchString);
        SearchJobsPage searchPageResults = new SearchJobsPage();
        Assert.assertTrue(searchPageResults.isPageLoaded());

        int count = searchPageResults.countRelevantResults(buildRegExp(searchString));
        DateFormat dateFormat = new SimpleDateFormat(dateOutputFormat);
        dataWriter.writeData((String.format(ouputFormat, dateFormat.format(new Date()), String.valueOf(count))));
    }

    @AfterTest
    public void tearDown() {
        Driver.getInstance().getWebDriver().quit();
    }
}
