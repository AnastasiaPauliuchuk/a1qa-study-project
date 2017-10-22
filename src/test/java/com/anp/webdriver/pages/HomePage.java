package com.anp.webdriver.pages;

import org.openqa.selenium.WebElement;

/**
 * Created by Anastasia Pauliuchuk on 10/21/2017.
 */
public class HomePage extends TutPage {


    private static final String JOBS_PAGE_SELECTOR = "//div[@id=\"mainmenu\"]/ul/li/a[contains(@href,\"jobs.tut.by\")]";


    @Override
    public boolean isPageLoaded() {
        return true;
    }

    public void goJobsPage() {
        WebElement element = getElementByXPath(JOBS_PAGE_SELECTOR);
        element.click();

    }
}
