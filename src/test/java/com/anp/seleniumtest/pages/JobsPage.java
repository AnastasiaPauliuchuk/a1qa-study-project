package com.anp.seleniumtest.pages;

import org.openqa.selenium.WebElement;

/**
 * Jobs page www.jobs.tut.by
 *
 * @author Anastasia Pauliuchuk
 * @since 10/19/2017
 */
public class JobsPage extends TutPage {

    /*marker selector for logo on jobs page*/
    private static final String MARKER_LOADED_XPATH = "span.navi-logo_jobs-tut-by";
    /*search input on  jobs.tut.by pages*/
    private static String SEARCH_INPUT_XPATH = "//form[contains(@action,\"/search/vacancy\")]/div/div/input[@type=\"text\"]";

    @Override
    public String getMarkerSelector() {
        return MARKER_LOADED_XPATH;
    }

    /**
     * Do search on jobs pages
     *
     * @param searchQuery
     */
    public void doSearch(String searchQuery) {
        doSearchByXPath(searchQuery, SEARCH_INPUT_XPATH);
    }

    /**
     * Do search on jobs pages by xPath
     *
     * @param searchQuery
     * @param xpath
     */
    private void doSearchByXPath(String searchQuery, String xpath) {
        WebElement searchInput = getElementByXPath(xpath);
        searchInput.sendKeys(searchQuery);
        searchInput.submit();
    }
}
