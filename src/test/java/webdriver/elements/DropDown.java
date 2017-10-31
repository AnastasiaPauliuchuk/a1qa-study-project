package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/28/2017.
 */
public class DropDown extends BaseElement {

    /**
     * Gets value of field
     *
     * @return value
     */
    private static Select selectUI = null;

    public DropDown(By loc) {
        super(loc);
    }

    public DropDown(By loc, String nameOf) {
        super(loc, nameOf);
    }

    public String getValue() {
        waitForIsElementPresent();
        if (selectUI == null) {
            selectUI = new Select(this.getElement());
        }
        return element.getText();
    }

    public void selectByValue(String value) {
        waitForIsElementPresent();
        if (selectUI == null) {
            selectUI = new Select(this.getElement());
        }
        selectUI.selectByValue(value);

    }

    @Override
    protected String getElementType() {
        return getLoc("loc.dropdown");
    }
}
