package task5.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/29/2017.
 */
public class SteamInstallForm extends BaseForm {

    private Button btnInstall = new Button(By.xpath("//a[@id=\"about_install_steam_link\"]"), "install button");

    public SteamInstallForm() {

        super(By.id("about_header_area"), "Steam Game Install");
    }

    public void clickInstall() {
        btnInstall.click();
    }

}
