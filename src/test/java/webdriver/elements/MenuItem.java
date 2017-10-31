package webdriver.elements;

import org.openqa.selenium.By;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/26/2017.
 */
public class MenuItem extends BaseElement {


    public MenuItem(By loc) {
        super(loc);
    }

    public MenuItem(By loc, String nameOf) {
        super(loc, nameOf);
    }

    public MenuItem(String stringLocator, String nameOfElement) {
        super(stringLocator, nameOfElement);
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.menuitem");
    }


}
