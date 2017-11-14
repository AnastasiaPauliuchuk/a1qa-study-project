package vk.pages;

import org.openqa.selenium.By;
import vk.menu.VKMainMenu;
import webdriver.elements.Label;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class VKAccountPage extends VKInnerPage {


    private static final By MAIN_MENU_LOC = By.id("top_profile_link");
    private Label lblMsgCount = new Label(By.xpath("//li[@id=\"l_msg\"]//span[contains(@class,\"inl_bl left_count\")]"), "lbl count");

    private VKMainMenu menuMain = new VKMainMenu(MAIN_MENU_LOC, "main menu");


    public VKAccountPage(By by, String s) {
        super(by, s);
    }

    public VKAccountPage() {
        super(By.id("side_bar"), "VK Account Page");
    }

    public VKMainMenu getMainMenu() {

        return menuMain;
    }

    public void assertMsgCountPresent() {

        assert (lblMsgCount.isVisible());
    }

    public void assertMsgCountNotPresent() {

        assert (!lblMsgCount.isPresent());
    }
}
