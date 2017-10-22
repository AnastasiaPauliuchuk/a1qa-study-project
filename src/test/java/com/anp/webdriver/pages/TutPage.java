package com.anp.webdriver.pages;

import com.anp.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.NoSuchElementException;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Anastasia Pauliuchuk on 10/19/2017.
 */
public abstract class TutPage implements PageTemplate {


    private static String SEARCH_INPUT_XPATH = "//form[@id=\"search\"]//input[@type=\"text\"]";

    public TutPage() {
    }

    abstract public boolean isPageLoaded();

    protected Wait<WebDriver> getWait() {

        return new FluentWait<WebDriver>(Driver.getInstance().getWebDriver())
                .withTimeout(Driver.getInstance().getTimeOut(), SECONDS)
                .pollingEvery(Driver.getInstance().getInterval(), SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    protected WebElement getElementByCSS(String cssSelector) {
        return getWait().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssSelector));
            }
        });
    }

    protected WebElement getElementByXPath(String xPath) {
        return getWait().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath(xPath));
            }
        });

    }

    ;


    protected void doSearchByXPath(String searchQuery, String xpath) {
        WebElement searchInput = getElementByXPath(xpath);
        searchInput.sendKeys(searchQuery);
        searchInput.submit();

    }

    public void doSearch(String searchQuery) {
        doSearchByXPath(searchQuery, SEARCH_INPUT_XPATH);
    }

}
