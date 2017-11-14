package vk.menu;

import org.openqa.selenium.By;
import webdriver.elements.Menu;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/10/2017.
 */
public class VKProfileMenu extends Menu {


    private static final String patternLocator = "//div[@id=\"top_profile_menu\"]/a[@id=\"top_%s_link\"]";


    public VKProfileMenu(By loc, String name) {
        super(loc, name);
    }

    @Override
    protected By getItemLocator(String itemName) {
        String xpath = String.format(patternLocator, itemName);
        return new By.ByXPath(xpath);
    }

    public void selectItem(Items item) {
        selectItem(item.toString(), "click");
    }

    @Override
    public String getElementType() {
        return getLoc("loc.widget");
    }


    public enum Items {
        MYPROFILE("myprofile"),
        LOGOUT("logout");

        public String value;

        Items(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }


}
