package webdriver.elements;

import org.openqa.selenium.By;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/27/2017.
 */
public class LabelList extends BaseElement {

    // private ArrayList<Label> items;

    public LabelList(By loc) {
        super(loc);
    }

    public LabelList(By loc, String nameOf) {
        super(loc, nameOf);
    }

    @Override
    public String getElementType() {
        return getLoc("loc.list");
    }

}
