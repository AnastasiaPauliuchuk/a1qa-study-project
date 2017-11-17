package webdriver.elements;

import org.openqa.selenium.By;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/26/2017.
 */
public abstract class Menu extends BaseElement {

    public Menu(By loc) {
        super(loc);
    }

    public Menu(By loc, String name) {
        super(loc, name);
    }

    @Override
    public String getElementType() {
        return getLoc("loc.menu");
    }

    protected abstract By getItemLocator(String itemName);

    protected MenuItem getItem(String itemName) {
        return new MenuItem(getItemLocator(itemName), itemName);
    }

    public void selectItem(final String itemName) {
        selectItem(itemName, "click");
    }

    public void selectItem(final String itemName, String action) {

        // waitForIsElementPresent();
        waitForIsElementVisible();
        info(String.format(getLoc("loc.menuitem") + " '%1$s'", itemName));
        MenuItem item = getItem(itemName);

        switch (action) {

            case ("hover"):
                item.hover();
                break;
            case ("click"):
            default:
                item.click();
                break;
        }
    }


}
