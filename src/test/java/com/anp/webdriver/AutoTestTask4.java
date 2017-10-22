package com.anp.webdriver;

import com.anp.reader.MyDataResourcePropReader;
import com.anp.webdriver.pages.HomePage;
import com.anp.webdriver.pages.JobsPage;
import com.anp.webdriver.pages.SearchJobsPage;
import com.anp.writer.MyDataFileWriter;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Anastasia Pauliuchuk on 10/17/2017.
 */
public class AutoTestTask4 {

    private static final String TEST_DATA_FILENAME = "task4.properties";

    private static final String searchString = "специалист по тестированию";
    private static final String regExpSearch = "([Сс]пециалист)(.*)(по)(.*)(тестированию)";


    private MyDataFileWriter dataWriter;
    private MyDataResourcePropReader dataReader;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {

        dataReader = new MyDataResourcePropReader(TEST_DATA_FILENAME);
        dataWriter = new MyDataFileWriter(dataReader.readProperty("outputFilename"));
        Driver.init(browser);
        Driver.getInstance().getWebDriver().navigate().to(dataReader.readProperty("testURL"));



    }

    @Test
    public void testTutBy() {

        // String str = dataReader.readProperty("searchString");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageLoaded());

        homePage.goJobsPage();
        JobsPage jobsPage = new JobsPage();
        Assert.assertTrue(jobsPage.isPageLoaded());


        jobsPage.doSearch(searchString);
        SearchJobsPage searchPageResults = new SearchJobsPage();
        Assert.assertTrue(searchPageResults.isPageLoaded());

        int count = searchPageResults.countRelevantResults(regExpSearch);
        System.out.println(count);
    }

    @AfterTest
    public void tearDown() {
        Driver.getInstance().getWebDriver().quit();
    }
}
