package vk.menu;

import org.openqa.selenium.By;
import webdriver.elements.Menu;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/9/2017.
 */
public class VKMainMenu extends Menu {


    private static final String patternLocator = "//*[@id=\"side_bar\"]//a[@href=\"/%s\"]";


    public VKMainMenu(By loc, String name) {
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

    public enum Items {
        MESSAGES("im"),
        FRIENDS("friends"),
        COMMUNITIES("groups");

        public String value;

        Items(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }


}
