package task5.forms;

import org.openqa.selenium.By;
import task5.elements.SteamGamesList;
import task5.elements.StreamGamesTabs;
import webdriver.BaseForm;

import java.util.Map;


/**
 * @author Anastasia Pauliuchuk
 *         created:  10/27/2017.
 */
public class SteamGamesListForm extends BaseForm {

    public StreamGamesTabs mTabs = new StreamGamesTabs(By.cssSelector("div.tabarea"), "games tabs");
    private SteamGamesList lblList = new SteamGamesList(By.xpath(
            "//div[@id=\"DiscountsRows\"]"), "discount games list ");


    public SteamGamesListForm() {
        super(By.cssSelector("div.tabarea"), "Steam Games ");

    }

    public Map<String, Double> getMaxDiscountValues() {
        return lblList.getMaxDiscountValues();
    }

    public void selectMaxDiscountItem() {

        lblList.createList();
        lblList.searchMaxDiscountGame();
        lblList.getMaxDiscountItem().click();
    }

}
