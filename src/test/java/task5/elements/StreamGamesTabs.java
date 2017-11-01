package task5.elements;

import org.openqa.selenium.By;
import webdriver.elements.Label;
import webdriver.elements.Menu;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/27/2017.
 */
public class StreamGamesTabs extends Menu {


    private static final String patternLocator = "//div[@id=\"tab_select_%s\"]";
    private static String patternTabContentLocator = "//div[@id=\"%sRows\"]";

    public StreamGamesTabs(By loc, String name) {
        super(loc, name);
    }

    @Override

    protected By getItemLocator(String itemName) {
        String xpath = String.format(patternLocator, itemName);
        return new By.ByXPath(xpath);
    }

    protected By getTabLocator(String itemName) {
        String xpath = String.format(patternTabContentLocator, itemName);
        return new By.ByXPath(xpath);
    }
    public void selectItem(StreamGamesTabs.Items item) {
        selectItem(item.toString());
    }

    public void assertTab(StreamGamesTabs.Items item) {
        waitForIsElementPresent();
        Label tab = new Label(getTabLocator(item.toString()), "tab " + item.toString());
        assert (tab.isPresent());
    }

    public enum Items {

        NEW_AND_TRENDING("PopularNewReleases"),
        TOP_SELLERS("TopSellers"),
        SPECIALS("Discounts"),
        NEW_RELEASES("NewReleases");

        public String value;

        Items(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }
}
