package task5.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.DropDown;
import webdriver.elements.Label;

import java.util.Date;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/28/2017.
 */

/* example of page http://store.steampowered.com/agecheck/app/271590/*/

public class SteamAgeForm extends BaseForm {


    private final static String ADULTYEAR = "1995";
    private DropDown ddYear = new DropDown(By.xpath("//select[@name=\"ageYear\"]"), "year select");
    private Button btnSubmit = new Button(By.xpath("//form[@id=\"agecheck_form\"]/a"), "age check button ");
    private boolean isDisplayed;


    public SteamAgeForm() {

        this(By.cssSelector("img.agegate_img_app"), "Age Form");

    }

    /*can not appear*/
    public SteamAgeForm(By locator, String formTitle) {
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

    public void selectAdultYear() {
        this.ddYear.selectByValue(ADULTYEAR);
        this.btnSubmit.click();
    }

}
