package com.anp.seleniumtest;

import com.anp.reader.MyDataResourcePropReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Singleton Driver class
 *
 * @author Anastasia Pauliuchuk
 * created 10/19/2017
 */
public class Driver {

    private static final String propFilename = "task4.properties";
    private static Driver driver;
    private static WebDriver webDriver;
    private static int timeOut;
    private static int interval;
    private static MyDataResourcePropReader propReader;

    private Driver() {
    }

    public static void init(String browserName) {
        propReader = new MyDataResourcePropReader(propFilename);

        timeOut = Integer.parseInt(propReader.readProperty("waitingTimeout"));
        interval = Integer.parseInt(propReader.readProperty("everyTimeInterval"));

        switch (browserName) {
            case ("firefox"):
                System.setProperty("webdriver.gecko.driver", propReader.readProperty("firefoxDriverPath"));
                webDriver = new FirefoxDriver();

                break;
            case ("chrome"):
                System.setProperty("webdriver.chrome.driver", propReader.readProperty("chromeDriverPath"));
                webDriver = new ChromeDriver();
                break;

        }
    }

    public static Driver getInstance() {
        if (driver == null) {
            driver = new Driver();
        }
        return driver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public int getInterval() {
        return interval;
    }

    public int getTimeOut() {
        return timeOut;
    }

}
