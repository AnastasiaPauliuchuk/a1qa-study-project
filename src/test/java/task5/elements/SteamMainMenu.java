package task5.elements;

import org.openqa.selenium.By;
import webdriver.elements.Menu;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/26/2017.
 */
public class SteamMainMenu extends Menu {

    private static final String patternLocator = "//div[@id='%s_tab']";


    public SteamMainMenu(By loc, String name) {
        super(loc, name);
    }

    @Override
    protected By getItemLocator(String itemName) {
        String xpath = String.format(patternLocator, itemName);
        return new By.ByXPath(xpath);
    }

    public void selectItem(Items item) {
        selectItem(item.toString(), "hover");
    }

    public enum Items {
        YOURSTORE("foryou"),
        GAMES("genre"),
        SOFTWARE("software");

        public String value;

        Items(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }


}
