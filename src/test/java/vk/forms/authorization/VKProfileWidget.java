package vk.forms.authorization;

import org.openqa.selenium.By;
import vk.menu.VKProfileMenu;
import webdriver.elements.BaseElement;
import webdriver.elements.Label;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/10/2017.
 */
public class VKProfileWidget extends BaseElement {

    private static final By PROFILE_MENU_LOC = By.id("top_profile_menu");
    private static Label lblProfileLink = new Label(By.cssSelector("a#top_profile_link"), "my profile");

    public void logout() {

        lblProfileLink.click();
        VKProfileMenu vkProfileMenu = new VKProfileMenu(PROFILE_MENU_LOC, "profile menu");

        vkProfileMenu.selectItem(VKProfileMenu.Items.LOGOUT);
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.widget");
    }
}
