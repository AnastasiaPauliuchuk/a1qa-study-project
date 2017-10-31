package task5.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.Date;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/30/2017.
 */

/* example of page http://store.steampowered.com/app/251570/
 */
public class SteamOnWorkForm extends BaseForm {


    private Button btnView = new Button(By.xpath("//*[@id=\"app_agegate\"]/div/a[contains(@onclick,\"Hide\")]"), "view page");


    private boolean isDisplayed;

    public SteamOnWorkForm() {

        this(By.id("app_agegate"), "Check On Work Content");

    }

    public SteamOnWorkForm(By locator, String formTitle) {
        super();
        long before = new Date().getTime();
        title = formTitle;
        Label titlePicture = new Label(locator, title);
        if (titlePicture.isPresent()) {
            isDisplayed = true;
            long openTime = new Date().getTime() - before;
            info(String.format(getLoc("loc.form.appears"), title) + String.format(" in %smsec", openTime));
        } else {
            isDisplayed = false;
            info(String.format(getLoc("loc.form.doesnt.appears"), title));
        }
    }


    public boolean isDisplayed() {
        return this.isDisplayed;

    }

    public void viewPage() {
        this.btnView.click();
    }

}
