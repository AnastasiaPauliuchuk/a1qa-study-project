package vk.forms.authorization;

import org.openqa.selenium.By;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class InnerLogin extends LoginWidget {


    private static TextBox tbLogin = new TextBox(By.id("quick_email"), "email/phone");
    private static TextBox tbPassword = new TextBox(By.id("quick_pass"), "password");
    private static Button btnLogin = new Button(By.id("quick_login_button"), "log in");

    @Override
    public TextBox getTBLogin() {
        return tbLogin;
    }

    @Override
    public TextBox getTBPassword() {
        return tbPassword;
    }

    @Override
    public Button getBTNLogin() {
        return btnLogin;
    }
}
