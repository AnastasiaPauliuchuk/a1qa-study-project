package vk.pages;

import org.openqa.selenium.By;
import vk.UserAccount;
import vk.forms.authorization.LoginWidget;
import vk.forms.authorization.MainLogin;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class VKHomePage extends VKPage {


    public VKHomePage() {
        super(By.cssSelector("div.top_home_logo"), "VK Home");
    }

    public void login(UserAccount user) {
        LoginWidget loginForm = new MainLogin();
        loginForm.login(user);

    }


}
