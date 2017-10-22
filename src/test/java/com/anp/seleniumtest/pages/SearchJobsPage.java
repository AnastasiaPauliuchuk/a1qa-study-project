package com.anp.seleniumtest.pages;

import com.anp.seleniumtest.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.anp.utils.Utils.removeCtrlSyms;

/**
 * Job search results page
 *
 * @author Anastasia Pauliuchuk
 * @since 10/19/2017
 */
public class SearchJobsPage extends JobsPage {

    /*Title of search result item*/
    private static final String SEARCH_RES_HEADER_CSS = "a.search-result-item__name";
    /*Marker-element, loaded after results*/
    private static final String MARKER_LOADED_XPATH = "div.search-suggestion__header";

    @Override
    public String getMarkerSelector() {
        return MARKER_LOADED_XPATH;
    }

    /**
     * Get list of search results by title css-selector
     *
     * @return
     */
    private List<WebElement> getAllResultsByCSS() {
        return Driver.getInstance().getWebDriver().findElements(By.cssSelector(SEARCH_RES_HEADER_CSS));
    }

    /**
     * Count result items by regexp title filter
     * @param regExpr
     * @return
     */
    public int countRelevantResults(String regExpr) {
        int count = 0;
        Pattern p = Pattern.compile(regExpr, Pattern.CASE_INSENSITIVE);
        Matcher m;
        List<WebElement> results = getAllResultsByCSS();
        for (WebElement element : results) {
            m = p.matcher(removeCtrlSyms(element.getText()));
            if (m.find())
                count++;
        }
        return count;
    }
}
