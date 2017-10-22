package com.anp.webdriver.pages;

import com.anp.utils.Utils;
import com.anp.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anastasia Pauliuchuk on 10/21/2017.
 */
public class SearchJobsPage extends JobsPage {


    private static final String SEARCH_RES_HEADER_CSS = "a.search-result-item__name";
    private static final String MARKER_LOADED_XPATH = "div.clusters";

    @Override
    public boolean isPageLoaded() {
        WebElement element = getWait().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(MARKER_LOADED_XPATH));
            }
        });
        return true;
    }

    private List<WebElement> getAllResultsByCSS() {

        return Driver.getInstance().getWebDriver().findElements(By.cssSelector(SEARCH_RES_HEADER_CSS));

    }

    ;


    public int countRelevantResults(String regExpr) {
        int count = 0;
        Pattern p = Pattern.compile(regExpr);
        Matcher m;
        List<WebElement> results = getAllResultsByCSS();
        for (WebElement element : results) {
            m = p.matcher(Utils.reduceCtrlSyms(element.getText()));
            if (m.find())
                count++;
        }
        return count;
    }
}
