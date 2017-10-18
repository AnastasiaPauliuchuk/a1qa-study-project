package com.anp.webdriver;

import com.anp.writer.MyDataFileWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Anastasia Pauliuchuk on 10/17/2017.
 */
public class AutoTestTask4 {

    private static final String URL = "www.tut.by";
    private static final String HOME_PAGE_LOGO_CSS_SELECTOR = "a.header-logo";
    private static final String JOBS_PAGE_HEADER_CSS_SELECTOR = "";
    private static final String OUTPUT_FILEPATH = "";
    private static WebDriver driver;
    private static MyDataFileWriter dataWriter;

    @BeforeTest
    @Parameters({"browser"})
    public static void setUp(String browser) {

        dataWriter = new MyDataFileWriter(OUTPUT_FILEPATH);
        switch (browser) {
            case ("firefox"):
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                driver = new FirefoxDriver();

                break;
            case ("chrome"):
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver2.33.exe");
                driver = new ChromeDriver();

                break;
            default:
                break;
        }

    }

    @Test
    public static void testTUTBY() {

        //  WebDriver driver = new ChromeDriver();

        driver.get("http://www.tut.by");
        // По-другому это можно сделать так:
        // driver.navigate().to("http://www.google.com");

        WebElement element;


        //Test if main page TUT.by loaded
        element = driver.findElement(By.cssSelector(HOME_PAGE_LOGO_CSS_SELECTOR));
        Assert.assertEquals(element.isDisplayed(), true);


        // Находим элемент по атрибуту name
        element = driver.findElement(By.id("search_from_str"));

        // Вводим текст
        element.sendKeys("Selenium");

        // Отправляем форму, при этом драйвер сам определит как отправить форму по элементу
        element.submit();

        // Проверяем тайтл страницы
        System.out.println("Page title is: " + driver.getTitle());

        // Страницы гугл динамически отрисовываются с помощью javascript
        // Ждем загрузки страницы с таймаутом в 10 секунд
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("selenium");
            }
        });

        // Ожидаем увидеть: "Selenium - Google Search"
        System.out.println("Page title is: " + driver.getTitle());


        // Закрываем браузер
        driver.quit();
    }

}
