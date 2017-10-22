package com.anp.seleniumtest.pages;

import org.openqa.selenium.WebElement;

/** Home page www.tut.by
 * @author Anastasia Pauliuchuk
 * @since 10/19/2017
 */
public class HomePage extends TutPage {

    /*menu item for jobs.tut.by link*/
    private static final String JOBS_PAGE_SELECTOR = "//div[@id=\"mainmenu\"]/ul/li/a[contains(@href,\"jobs.tut.by\")]";
    /*marker selector for logo on home page*/
    private static final String MARKER_LOADED_XPATH = "a.header-logo";

    @Override
    public String getMarkerSelector() {
        return MARKER_LOADED_XPATH;
    }

    /**
     * Clicks on menu item for www.jobs.tut.by
     */
    public void goJobsPage() {
        WebElement element = getElementByXPath(JOBS_PAGE_SELECTOR);
        element.click();
    }
}
