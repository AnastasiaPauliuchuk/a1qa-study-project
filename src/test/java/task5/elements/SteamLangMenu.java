package task5.elements;

import org.openqa.selenium.By;
import webdriver.elements.Menu;
import webdriver.elements.MenuItem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/31/2017.
 */
public class SteamLangMenu extends Menu {

    private static final String patternLocator = "//div[@id=\"language_dropdown\"]/div/a[contains(@href,\"%s\")]";
    private static Map<String, String> langMap;

    static {
        langMap = new HashMap<String, String>();
        langMap.put("en", "english");
        langMap.put("ru", "russian");
    }

    public SteamLangMenu(By loc, String name) {
        super(loc, name);
    }

    @Override
    protected By getItemLocator(String itemName) {
        String xpath = String.format(patternLocator, itemName);
        return new By.ByXPath(xpath);
    }

    public void selectItem(String itemName) {
        super.selectItem(itemName);
    }

    public boolean isCurrentLanguage(String lang) {

        MenuItem item = getItem(langMap.get(lang));
        return !(item.isPresent());

    }

}
