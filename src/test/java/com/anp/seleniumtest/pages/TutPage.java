package com.anp.seleniumtest.pages;

import com.anp.seleniumtest.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Abstract class for tut.by pages, implementation {@link PageTemplate}
 *
 * @author Anastasia Pauliuchuk
 * @since 10/19/2017
 */
public abstract class TutPage implements PageTemplate {

    /**
     * Get css-selector for marker-element
     * ( overridden in inheritors)
     *
     * @return
     */
    public abstract String getMarkerSelector();

    /**
     * Checks if marker-element displayed on page
     *
     * @return
     */
    public boolean isPageLoaded() {
        WebElement element = getElementByCSS(getMarkerSelector());
        return element.isDisplayed();
    }

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

}
