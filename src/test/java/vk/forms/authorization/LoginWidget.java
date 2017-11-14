package vk.forms.authorization;

import vk.UserAccount;
import vk.utils.LocalManager;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public abstract class LoginWidget extends BaseElement {


    @Override
    protected String getElementType() {
        return getLoc("loc.widget");
    }

    public abstract TextBox getTBLogin();

    public abstract TextBox getTBPassword();

    public abstract Button getBTNLogin();

    public void login(String login, String password) {
        getTBLogin().type(login);
        getTBPassword().type(password);
        getBTNLogin().click();


    }

    public void login(UserAccount user) {
        LocalManager.getInstance().setLang(user.getLang());
        login(user.getLogin(), user.getPassword());

    }

}
