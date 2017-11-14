package vk.pages;

import org.openqa.selenium.By;
import vk.UserAccount;
import vk.forms.authorization.VKProfileWidget;
import webdriver.BaseForm;
import webdriver.Browser;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/10/2017.
 */
public abstract class VKPage extends BaseForm {

    public VKPage(By by, String s) {
        super(by, s);
    }

    public abstract void login(UserAccount user);

    public void logout() {
        VKProfileWidget wProfile = new VKProfileWidget();
        wProfile.logout();
    }

    public void goToURL(String url) {
        Browser.getInstance().getDriver().navigate().to(url);
    }
}
