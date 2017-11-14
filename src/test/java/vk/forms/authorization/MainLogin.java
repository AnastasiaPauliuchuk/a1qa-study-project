package vk.forms.authorization;

import org.openqa.selenium.By;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class MainLogin extends LoginWidget {

    private static TextBox tbLogin = new TextBox(By.id("index_email"));
    private static TextBox tbPassword = new TextBox(By.id("index_pass"));
    private static Button btnLogin = new Button(By.id("index_login_button"));


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
