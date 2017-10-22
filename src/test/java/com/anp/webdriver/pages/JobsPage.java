package com.anp.webdriver.pages;

/**
 * Created by Anastasia Pauliuchuk on 10/21/2017.
 */
public class JobsPage extends TutPage {

    private static String SEARCH_INPUT_XPATH = "//form[contains(@action,\"/search/vacancy\")]/div/div/input[@type=\"text\"]";

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    @Override
    public void doSearch(String searchQuery) {
        doSearchByXPath(searchQuery, SEARCH_INPUT_XPATH);
    }


}
