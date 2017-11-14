package vk.pages;

import org.openqa.selenium.By;
import vk.UserAccount;
import vk.forms.authorization.InnerLogin;
import vk.forms.authorization.LoginWidget;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/11/2017.
 */
public class VKInnerPage extends VKPage {


    public VKInnerPage() {
        super(By.id("side_bar"), "VK Inner");
    }


    public VKInnerPage(By by, String s) {
        super(by, s);
    }

    public void login(UserAccount user) {
        LoginWidget loginForm = new InnerLogin();
        loginForm.login(user);

    }
}
