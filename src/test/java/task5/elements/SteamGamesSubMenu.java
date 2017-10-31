package task5.elements;

import org.openqa.selenium.By;
import task5.TagsDictionary;
import webdriver.PropertiesResourceManager;
import webdriver.elements.Label;
import webdriver.elements.Menu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/27/2017.
 */
public class SteamGamesSubMenu extends Menu {


    private static final String PROPERTIES_FILE = "selenium.properties";
    private static final String patternLocator = "//div[@id=\"genre_flyout\"]/div/a[contains(@href,\"%s\")]";
    private TagsDictionary tagsDictionary = new TagsDictionary(new PropertiesResourceManager(PROPERTIES_FILE).getProperty("site.language"));
    private Label lblSubmenu = new Label(By.xpath("//div[@id=\"genre_flyout\"]"), "submenu");

    public SteamGamesSubMenu(By loc, String name) {
        super(loc, name);
    }

    public void assertSubMenu() {
        waitForIsElementPresent();
        lblSubmenu.waitForIsElementPresent();
        assert (lblSubmenu.isPresent());
    }

    @Override
    protected By getItemLocator(String itemName) {
        String xpath = null;
        try {
            xpath = String.format(patternLocator, URLEncoder.encode(itemName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new By.ByXPath(xpath);
    }

    public void selectItem(SteamGamesSubMenu.Items item) {
        selectItem(tagsDictionary.translate(item.toString()));
    }

    public enum Items {

        ACTION("action"), ADVENTURE("adventure"), CASUAL("casual");

        public String value;

        Items(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }
}
